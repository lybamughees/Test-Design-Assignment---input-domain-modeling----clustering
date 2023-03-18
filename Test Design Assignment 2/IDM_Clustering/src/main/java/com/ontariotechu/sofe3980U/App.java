package com.ontariotechu.sofe3980U;

import net.sf.javaml.core.Dataset;

import net.sf.javaml.tools.data.FileHandler;

import java.io.File;
import java.io.IOException;

import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.Cobweb;
import net.sf.javaml.clustering.DensityBasedSpatialClustering;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.clustering.evaluation.ClusterEvaluation;
import net.sf.javaml.clustering.evaluation.SumOfSquaredErrors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        /* Load a dataset */
Dataset data;

    data = FileHandler.loadDataset(new File("src/devtools/iris.data"), 4, ",");
    /* Create a new instance of the KMeans algorithm, with no options
  * specified. By default this will generate 4 clusters. */
Clusterer km = new KMeans();
Clusterer cobweb= new Cobweb();
Dataset[] CW = cobweb.cluster(data);
Clusterer DBSC= new DensityBasedSpatialClustering();
Dataset[] DB = DBSC.cluster(data);


/* Cluster the data, it will be returned as an array of data sets, with
  * each dataset representing a cluster. */
Dataset[] clusters = km.cluster(data);
System.out.println("\nK Means Cluster\n");
printClusters(clusters);
System.out.println("\nEVALUATION:");
ClusterEvaluation sse= new SumOfSquaredErrors();
double score=sse.score(clusters);
System.out.println("\nSSE Score: " + score);
System.out.println("\nCobweb Cluster\n");
printClusters(CW);
System.out.println("\nEVALUATION:");
ClusterEvaluation cob= new SumOfSquaredErrors();
double cobs=cob.score(CW);
System.out.println("\nSSE Score: " + cobs);
System.out.println("\nDensityBasedSpatialClustering Cluster\n");
printClusters(DB);
System.out.println("\nEVALUATION:");
ClusterEvaluation dbsc= new SumOfSquaredErrors();
double dbscs=dbsc.score(DB);
System.out.println("\nSSE Score: " + dbscs);


    }
    static void printClusters(Dataset[] clusters){
        System.out.println("Cluster Count: " + clusters.length+ "\n");
        int count=1;
       for (Dataset dataset:clusters){
        System.out.println("\n--Cluster " + count + " ---");
        count++;
        System.out.println("\n"+ dataset);
       
        }
    }
  }
    
    
    




