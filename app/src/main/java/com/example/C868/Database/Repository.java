package com.example.C868.Database;

import android.app.Application;

import com.example.C868.DAO.AssemblyPartsDAO;
import com.example.C868.DAO.PartsDAO;
import com.example.C868.DAO.PurchasedComponentsDAO;
import com.example.C868.Entity.AssemblyParts;
import com.example.C868.Entity.Parts;
import com.example.C868.Entity.PurchasedComponents;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private static volatile Repository repository;

    private PartsDAO mPartsDAO;
    private AssemblyPartsDAO mAssemblyPartsDAO;
    private PurchasedComponentsDAO mPurchasedComponentsDAO;

    private List<Parts> mAllParts;
    private List<AssemblyParts> mAllAssemblyParts;
    private List<PurchasedComponents> mAllPurchasedComponents;

    private static int NUMBER_OF_THREADS = 2;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        MRPDatabase database = MRPDatabase.getDatabase(application);
    }
}
