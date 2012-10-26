package org.apache.mahout.clustering.elkan;
import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.mahout.clustering.Cluster;
import org.apache.mahout.clustering.classify.ClusterClassifier;
import org.apache.mahout.clustering.iterator.ClusterWritable;
import org.apache.mahout.common.iterator.sequencefile.PathFilters;
import org.apache.mahout.common.iterator.sequencefile.PathType;
import org.apache.mahout.common.iterator.sequencefile.SequenceFileDirValueIterable;

import com.google.common.collect.Lists;

public class ElkanClassifier extends ClusterClassifier{
	
	public static String ELKAN_VECTORS_DIR="elkanVectors-";
	public static String ELKAN_STEP_1="step-1";
	public static String ELKAN_STEP_2="step-2";
	public static String ELKAN_STEP_3="step-3";
	public static String ELKAN_STEP_4="step-4";
	
	public List<Cluster> readNewModelsFromSeqFiles(Configuration conf, Path path) throws IOException {
	    Configuration config = new Configuration();
	    List<Cluster> clusters = Lists.newArrayList();
	    for (ClusterWritable cw : new SequenceFileDirValueIterable<ClusterWritable>(path, PathType.LIST,
	        PathFilters.logsCRCFilter(), config)) {
	      Cluster cluster = cw.getValue();
	      cluster.configure(conf);
	      clusters.add(cluster);
	    }
	    return clusters;	    
	  }

}
