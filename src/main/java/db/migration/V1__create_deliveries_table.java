package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Statement;

public class V1__create_deliveries_table extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        final Statement statement = context.getConnection().createStatement();

        try (statement) {
            statement.execute("create table deliveries" +
                    "(id bigserial not null constraint orders_pkey primary key," +
                    "order_id bigint)");
        }
    }
}
