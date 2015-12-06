package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;


import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception.ExpenseManagerException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.database.DatabaseHandler;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.DatabaseAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.DatabaseTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;

/**
 * Created by Supun on 12/4/2015.
 */
public class PersistentExpenseManager extends ExpenseManager {

    private DatabaseHandler dbhlp;

    public PersistentExpenseManager(DatabaseHandler dbhlp) {
        this.dbhlp = dbhlp;
        try {
            setup();
        } catch (ExpenseManagerException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void setup() throws ExpenseManagerException {

        TransactionDAO inDatabaseTransactionDAO = new DatabaseTransactionDAO(dbhlp);
        setTransactionsDAO(inDatabaseTransactionDAO);


        AccountDAO inDatabaseAccountDAO = new DatabaseAccountDAO(dbhlp);
        setAccountsDAO(inDatabaseAccountDAO);

        // dummy data
        Account dummyAcct1 = new Account("12345A", "Yoda Bank", "Anakin Skywalker", 10000.0);
        Account dummyAcct2 = new Account("78945Z", "Clone BC", "Obi-Wan Kenobi", 80000.0);
        getAccountsDAO().addAccount(dummyAcct1);
        getAccountsDAO().addAccount(dummyAcct2);

    }
}