package com.example.C868.Database;

import android.app.Application;

import com.example.C868.DAO.AssemblyPartsDAO;
import com.example.C868.DAO.PurchasedComponentsDAO;
import com.example.C868.Entity.AssemblyParts;
import com.example.C868.Entity.PurchasedComponents;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    private final AssemblyPartsDAO mAssemblyPartsDAO;
    private final PurchasedComponentsDAO mPurchasedComponentsDAO;

    private List<AssemblyParts> mAllAssemblyParts;
    private List<PurchasedComponents> mAllPurchasedComponents;

    private static final int NUMBER_OF_THREADS = 2;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        MRPDatabase database = MRPDatabase.getDatabase(application);

        mAssemblyPartsDAO = database.assemblyPartsDAO();
        mPurchasedComponentsDAO = database.purchasedComponentsDAO();
    }

    /**
    public List<Parts> getmAllPart() {
        databaseExecutor.execute(() -> mAllParts = (List<Parts>) mPartsDAO.getAllParts());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllParts;
    }*/

    public List<AssemblyParts> getmAllAssemblyParts() {
        databaseExecutor.execute(() ->
                mAllAssemblyParts = mAssemblyPartsDAO.getAllAssemblyParts());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssemblyParts;
    }

    public List<PurchasedComponents> getmAllPurchasedComponents() {
        databaseExecutor.execute(() -> mAllPurchasedComponents = (List<PurchasedComponents>) mPurchasedComponentsDAO.getAllPurchasedComponents());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllPurchasedComponents;
    }

    /**public void insert(Parts parts) {
        databaseExecutor.execute(() -> {
            mPartsDAO.insert(parts);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }*/


    /**public void update(Parts parts) {
        databaseExecutor.execute(() -> {
            mPartsDAO.update(parts);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }*/

    /**public void delete(Parts parts) {
        databaseExecutor.execute(() -> {
            mPartsDAO.delete(parts);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }*/

    public void insert(AssemblyParts assemblyParts) {
        databaseExecutor.execute(() -> {
            mAssemblyPartsDAO.insert(assemblyParts);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void update(AssemblyParts assemblyParts) {
        databaseExecutor.execute(() -> {
            mAssemblyPartsDAO.update(assemblyParts);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void delete(AssemblyParts assemblyParts) {
        databaseExecutor.execute(() -> {
            mAssemblyPartsDAO.delete(assemblyParts);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void insert(PurchasedComponents purchasedComponents) {
        databaseExecutor.execute(() -> {
            mPurchasedComponentsDAO.insert(purchasedComponents);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void update(PurchasedComponents purchasedComponents) {
        databaseExecutor.execute(() -> {
            mPurchasedComponentsDAO.update(purchasedComponents);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void delete(PurchasedComponents purchasedComponents) {
        databaseExecutor.execute(() -> {
            mPurchasedComponentsDAO.delete(purchasedComponents);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void getAssemblyPartsCount() {
        databaseExecutor.execute(() -> {
            mAssemblyPartsDAO.getCountOfAssemblyParts();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}