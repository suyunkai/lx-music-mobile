package com.wanos.careditproject;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.wanos.careditproject.databinding.ItemCreatorHomeCardItemBindingImpl;
import com.wanos.careditproject.databinding.ItemCreatorHomeCardListBindingImpl;
import com.wanos.careditproject.databinding.ItemCreatorHomeHeaderBindingImpl;
import com.wanos.careditproject.databinding.ItemCreatorHomeTagItemBindingImpl;
import com.wanos.careditproject.databinding.ItemCreatorHomeTagListBindingImpl;
import com.wanos.careditproject.databinding.ItemCreatorHomeTitleBindingImpl;
import com.wanos.careditproject.databinding.ItemCreatorMineWorkBindingImpl;
import com.wanos.careditproject.databinding.PopCreatorAuditMoreBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ITEMCREATORHOMECARDITEM = 1;
    private static final int LAYOUT_ITEMCREATORHOMECARDLIST = 2;
    private static final int LAYOUT_ITEMCREATORHOMEHEADER = 3;
    private static final int LAYOUT_ITEMCREATORHOMETAGITEM = 4;
    private static final int LAYOUT_ITEMCREATORHOMETAGLIST = 5;
    private static final int LAYOUT_ITEMCREATORHOMETITLE = 6;
    private static final int LAYOUT_ITEMCREATORMINEWORK = 7;
    private static final int LAYOUT_POPCREATORAUDITMORE = 8;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(8);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.item_creator_home_card_item, 1);
        sparseIntArray.put(R.layout.item_creator_home_card_list, 2);
        sparseIntArray.put(R.layout.item_creator_home_header, 3);
        sparseIntArray.put(R.layout.item_creator_home_tag_item, 4);
        sparseIntArray.put(R.layout.item_creator_home_tag_list, 5);
        sparseIntArray.put(R.layout.item_creator_home_title, 6);
        sparseIntArray.put(R.layout.item_creator_mine_work, 7);
        sparseIntArray.put(R.layout.pop_creator_audit_more, 8);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag == null) {
            throw new RuntimeException("view must have a tag");
        }
        switch (i2) {
            case 1:
                if ("layout/item_creator_home_card_item_0".equals(tag)) {
                    return new ItemCreatorHomeCardItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_creator_home_card_item is invalid. Received: " + tag);
            case 2:
                if ("layout/item_creator_home_card_list_0".equals(tag)) {
                    return new ItemCreatorHomeCardListBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_creator_home_card_list is invalid. Received: " + tag);
            case 3:
                if ("layout/item_creator_home_header_0".equals(tag)) {
                    return new ItemCreatorHomeHeaderBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_creator_home_header is invalid. Received: " + tag);
            case 4:
                if ("layout/item_creator_home_tag_item_0".equals(tag)) {
                    return new ItemCreatorHomeTagItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_creator_home_tag_item is invalid. Received: " + tag);
            case 5:
                if ("layout/item_creator_home_tag_list_0".equals(tag)) {
                    return new ItemCreatorHomeTagListBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_creator_home_tag_list is invalid. Received: " + tag);
            case 6:
                if ("layout/item_creator_home_title_0".equals(tag)) {
                    return new ItemCreatorHomeTitleBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_creator_home_title is invalid. Received: " + tag);
            case 7:
                if ("layout/item_creator_mine_work_0".equals(tag)) {
                    return new ItemCreatorMineWorkBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_creator_mine_work is invalid. Received: " + tag);
            case 8:
                if ("layout/pop_creator_audit_more_0".equals(tag)) {
                    return new PopCreatorAuditMoreBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for pop_creator_audit_more is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(2);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "data");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> map = new HashMap<>(8);
            sKeys = map;
            map.put("layout/item_creator_home_card_item_0", Integer.valueOf(R.layout.item_creator_home_card_item));
            map.put("layout/item_creator_home_card_list_0", Integer.valueOf(R.layout.item_creator_home_card_list));
            map.put("layout/item_creator_home_header_0", Integer.valueOf(R.layout.item_creator_home_header));
            map.put("layout/item_creator_home_tag_item_0", Integer.valueOf(R.layout.item_creator_home_tag_item));
            map.put("layout/item_creator_home_tag_list_0", Integer.valueOf(R.layout.item_creator_home_tag_list));
            map.put("layout/item_creator_home_title_0", Integer.valueOf(R.layout.item_creator_home_title));
            map.put("layout/item_creator_mine_work_0", Integer.valueOf(R.layout.item_creator_mine_work));
            map.put("layout/pop_creator_audit_more_0", Integer.valueOf(R.layout.pop_creator_audit_more));
        }
    }
}
