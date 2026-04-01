package cn.toside.music.mobile.spatialAudio;

import android.util.Log;

/**
 * 声道重映射/补足引擎
 * 当音源声道布局与输出声道布局不匹配时，生成混音矩阵进行智能映射
 *
 * 策略：
 * 1. 匹配的声道直接映射（1:1，增益 0dB）
 * 2. 多余的源声道降混到最近的目标声道（增益 -3dB ~ -6dB）
 * 3. 缺失的目标声道从相近源声道派生（增益 -3dB ~ -6dB）
 */
public class ChannelRemapper {

    private static final String TAG = "ChannelRemapper";

    // 增益常量
    private static final float GAIN_DIRECT  = 1.0f;       // 0dB  直接映射
    private static final float GAIN_MINUS3  = 0.707f;     // -3dB 近邻混合
    private static final float GAIN_MINUS6  = 0.5f;       // -6dB 远邻混合
    private static final float GAIN_MINUS10 = 0.316f;     // -10dB 微弱补充

    /**
     * 构建混音矩阵
     * @param source 源声道布局（音频文件的布局）
     * @param target 目标声道布局（输出硬件/选择的布局）
     * @return float[targetCh][sourceCh] 混音矩阵，output[t] = sum(matrix[t][s] * input[s])
     */
    public static float[][] buildMixMatrix(ChannelLayout source, ChannelLayout target) {
        int srcCh = source.getChannelCount();
        int tgtCh = target.getChannelCount();
        String[] srcNames = source.getChannelNames();
        String[] tgtNames = target.getChannelNames();

        float[][] matrix = new float[tgtCh][srcCh];

        // 第一遍：直接映射匹配的声道
        boolean[] srcMapped = new boolean[srcCh];
        boolean[] tgtMapped = new boolean[tgtCh];

        for (int t = 0; t < tgtCh; t++) {
            for (int s = 0; s < srcCh; s++) {
                if (tgtNames[t].equals(srcNames[s])) {
                    matrix[t][s] = GAIN_DIRECT;
                    srcMapped[s] = true;
                    tgtMapped[t] = true;
                    break;
                }
            }
        }

        // 第二遍：处理未映射的源声道（降混到最近的已有目标声道）
        for (int s = 0; s < srcCh; s++) {
            if (srcMapped[s]) continue;
            applyDownmixRules(matrix, srcNames[s], s, tgtNames, tgtCh);
        }

        // 第三遍：处理未映射的目标声道（从已有源声道派生）
        for (int t = 0; t < tgtCh; t++) {
            if (tgtMapped[t]) continue;
            applyUpmixRules(matrix, tgtNames[t], t, srcNames, srcCh);
        }

        logMatrix(source, target, matrix);
        return matrix;
    }

    /**
     * 降混规则：将多余的源声道混入目标声道
     */
    private static void applyDownmixRules(float[][] matrix, String srcName, int srcIdx,
                                           String[] tgtNames, int tgtCh) {
        switch (srcName) {
            case "SL": // 侧左 → 后左
                addToTarget(matrix, srcIdx, tgtNames, tgtCh, "BL", GAIN_MINUS3);
                addToTarget(matrix, srcIdx, tgtNames, tgtCh, "FL", GAIN_MINUS6);
                break;
            case "SR": // 侧右 → 后右
                addToTarget(matrix, srcIdx, tgtNames, tgtCh, "BR", GAIN_MINUS3);
                addToTarget(matrix, srcIdx, tgtNames, tgtCh, "FR", GAIN_MINUS6);
                break;
            case "TFL": // 天空前左 → 前左
                addToTarget(matrix, srcIdx, tgtNames, tgtCh, "FL", GAIN_MINUS6);
                addToTarget(matrix, srcIdx, tgtNames, tgtCh, "FC", GAIN_MINUS10);
                break;
            case "TFR": // 天空前右 → 前右
                addToTarget(matrix, srcIdx, tgtNames, tgtCh, "FR", GAIN_MINUS6);
                addToTarget(matrix, srcIdx, tgtNames, tgtCh, "FC", GAIN_MINUS10);
                break;
            case "TBL": // 天空后左 → 后左
                addToTarget(matrix, srcIdx, tgtNames, tgtCh, "BL", GAIN_MINUS6);
                addToTarget(matrix, srcIdx, tgtNames, tgtCh, "SL", GAIN_MINUS6);
                break;
            case "TBR": // 天空后右 → 后右
                addToTarget(matrix, srcIdx, tgtNames, tgtCh, "BR", GAIN_MINUS6);
                addToTarget(matrix, srcIdx, tgtNames, tgtCh, "SR", GAIN_MINUS6);
                break;
            case "BC": // 后中 → 后左+后右
                addToTarget(matrix, srcIdx, tgtNames, tgtCh, "BL", GAIN_MINUS3);
                addToTarget(matrix, srcIdx, tgtNames, tgtCh, "BR", GAIN_MINUS3);
                break;
            case "FWL": // 前宽左 → 前左+侧左
                addToTarget(matrix, srcIdx, tgtNames, tgtCh, "FL", GAIN_MINUS3);
                addToTarget(matrix, srcIdx, tgtNames, tgtCh, "SL", GAIN_MINUS6);
                break;
            case "FWR": // 前宽右 → 前右+侧右
                addToTarget(matrix, srcIdx, tgtNames, tgtCh, "FR", GAIN_MINUS3);
                addToTarget(matrix, srcIdx, tgtNames, tgtCh, "SR", GAIN_MINUS6);
                break;
            default:
                Log.w(TAG, "Unknown source channel for downmix: " + srcName);
                break;
        }
    }

    /**
     * 补足规则：为缺失的目标声道从源声道派生
     */
    private static void applyUpmixRules(float[][] matrix, String tgtName, int tgtIdx,
                                         String[] srcNames, int srcCh) {
        switch (tgtName) {
            case "SL": // 侧左 ← 后左派生
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "BL", GAIN_MINUS3);
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "FL", GAIN_MINUS10);
                break;
            case "SR": // 侧右 ← 后右派生
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "BR", GAIN_MINUS3);
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "FR", GAIN_MINUS10);
                break;
            case "TFL": // 天空前左 ← 前左+中置
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "FL", GAIN_MINUS6);
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "FC", GAIN_MINUS10);
                break;
            case "TFR": // 天空前右 ← 前右+中置
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "FR", GAIN_MINUS6);
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "FC", GAIN_MINUS10);
                break;
            case "TBL": // 天空后左 ← 后左+侧左
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "BL", GAIN_MINUS6);
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "SL", GAIN_MINUS6);
                break;
            case "TBR": // 天空后右 ← 后右+侧右
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "BR", GAIN_MINUS6);
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "SR", GAIN_MINUS6);
                break;
            case "BC": // 后中 ← 后左+后右
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "BL", GAIN_MINUS3);
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "BR", GAIN_MINUS3);
                break;
            case "BL": // 后左 ← 侧左派生
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "SL", GAIN_MINUS3);
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "FL", GAIN_MINUS10);
                break;
            case "BR": // 后右 ← 侧右派生
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "SR", GAIN_MINUS3);
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "FR", GAIN_MINUS10);
                break;
            case "FWL": // 前宽左 ← 前左+侧左
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "FL", GAIN_MINUS3);
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "SL", GAIN_MINUS6);
                break;
            case "FWR": // 前宽右 ← 前右+侧右
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "FR", GAIN_MINUS3);
                addFromSource(matrix, tgtIdx, srcNames, srcCh, "SR", GAIN_MINUS6);
                break;
            default:
                Log.w(TAG, "Unknown target channel for upmix: " + tgtName);
                break;
        }
    }

    /** 将源声道按增益添加到目标（按名称查找目标索引） */
    private static void addToTarget(float[][] matrix, int srcIdx,
                                     String[] tgtNames, int tgtCh,
                                     String targetName, float gain) {
        for (int t = 0; t < tgtCh; t++) {
            if (tgtNames[t].equals(targetName)) {
                matrix[t][srcIdx] += gain;
                return;
            }
        }
        // 目标声道不存在，忽略
    }

    /** 从源声道按增益派生到目标（按名称查找源索引） */
    private static void addFromSource(float[][] matrix, int tgtIdx,
                                       String[] srcNames, int srcCh,
                                       String sourceName, float gain) {
        for (int s = 0; s < srcCh; s++) {
            if (srcNames[s].equals(sourceName)) {
                matrix[tgtIdx][s] += gain;
                return;
            }
        }
        // 源声道不存在，忽略
    }

    /** 日志输出混音矩阵 */
    private static void logMatrix(ChannelLayout source, ChannelLayout target, float[][] matrix) {
        StringBuilder sb = new StringBuilder();
        sb.append("Mix matrix: ").append(source.getDisplayName())
          .append(" -> ").append(target.getDisplayName()).append("\n");
        String[] srcNames = source.getChannelNames();
        String[] tgtNames = target.getChannelNames();
        for (int t = 0; t < matrix.length; t++) {
            sb.append("  ").append(String.format("%-4s", tgtNames[t])).append(" = ");
            boolean first = true;
            for (int s = 0; s < matrix[t].length; s++) {
                if (matrix[t][s] != 0) {
                    if (!first) sb.append(" + ");
                    sb.append(String.format("%.2f*%s", matrix[t][s], srcNames[s]));
                    first = false;
                }
            }
            if (first) sb.append("(silence)");
            sb.append("\n");
        }
        Log.d(TAG, sb.toString());
    }
}
