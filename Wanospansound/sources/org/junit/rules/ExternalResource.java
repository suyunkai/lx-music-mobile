package org.junit.rules;

import java.util.ArrayList;
import org.junit.runner.Description;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ExternalResource implements TestRule {
    protected void after() {
    }

    protected void before() throws Throwable {
    }

    @Override // org.junit.rules.TestRule
    public Statement apply(Statement statement, Description description) {
        return statement(statement);
    }

    private Statement statement(final Statement statement) {
        return new Statement() { // from class: org.junit.rules.ExternalResource.1
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:7:0x0016 -> B:11:0x0023). Please report as a decompilation issue!!! */
            @Override // org.junit.runners.model.Statement
            public void evaluate() throws Exception {
                ExternalResource.this.before();
                ArrayList arrayList = new ArrayList();
                try {
                    try {
                        statement.evaluate();
                        ExternalResource.this.after();
                    } catch (Throwable th) {
                        arrayList.add(th);
                    }
                } catch (Throwable th2) {
                    try {
                        arrayList.add(th2);
                        ExternalResource.this.after();
                    } catch (Throwable th3) {
                        try {
                            ExternalResource.this.after();
                        } catch (Throwable th4) {
                            arrayList.add(th4);
                        }
                        throw th3;
                    }
                }
                MultipleFailureException.assertEmpty(arrayList);
            }
        };
    }
}
