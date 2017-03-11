package Database;

import Database.JooqGenerated.Tables;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public class User {
    private Sqlite sqlite;

    public User(Sqlite sqlite) {
        this.sqlite = sqlite;
    }

    public boolean isUserCredentialsValid(String name, String password) {
        DSLContext dsl = DSL.using(this.sqlite.getConnection(), SQLDialect.SQLITE);
        Result<Record1<Integer>> fetch = dsl.selectOne().from(Tables.CUSTOMER).fetch();
        return fetch.size() == 1;
    }
}
