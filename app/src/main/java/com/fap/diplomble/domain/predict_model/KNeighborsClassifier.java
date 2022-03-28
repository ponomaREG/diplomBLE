package com.fap.diplomble.domain.predict_model;

import java.util.*;


public class KNeighborsClassifier extends AbstractPredictedModel<Integer> {

    public static KNeighborsClassifier get() {
        double[][] X = {{-84, -70, -51, -73}, {-67, -78, -83, -85}, {-72, -66, -68, -68}, {-68, -62, -71, -66}, {-64, -68, -68, -50}, {-70, -62, -60, -59}, {-65, -68, -64, -71}, {-71, -64, -62, -76}, {-70, -65, -63, -77}, {-71, -71, -63, -61}, {-69, -63, -65, -77}, {-64, -68, -71, -68}, {-71, -64, -62, -76}, {-73, -67, -74, -63}, {-64, -66, -67, -49}, {-67, -67, -71, -49}, {-67, -67, -65, -71}, {-69, -64, -70, -71}, {-67, -62, -69, -66}, {-70, -64, -62, -71}, {-65, -49, -71, -74}, {-83, -70, -54, -75}, {-68, -65, -62, -76}, {-68, -77, -78, -85}, {-72, -68, -59, -63}, {-68, -68, -70, -73}, {-66, -67, -72, -69}, {-66, -66, -71, -68}, {-64, -68, -72, -68}, {-69, -63, -64, -77}, {-70, -64, -62, -72}, {-66, -76, -82, -86}, {-74, -69, -69, -68}, {-66, -68, -67, -52}, {-82, -68, -57, -76}, {-67, -68, -64, -52}, {-68, -62, -70, -66}, {-69, -64, -60, -58}, {-67, -82, -80, -83}, {-64, -67, -68, -50}, {-68, -64, -67, -78}, {-65, -49, -73, -72}, {-84, -68, -53, -74}, {-67, -70, -70, -52}, {-70, -73, -62, -61}, {-70, -66, -62, -74}, {-66, -50, -74, -70}, {-70, -71, -61, -60}, {-65, -66, -71, -68}, {-68, -64, -67, -79}, {-66, -65, -72, -49}, {-68, -82, -80, -83}, {-67, -74, -85, -86}, {-71, -62, -60, -60}, {-68, -65, -63, -70}, {-68, -78, -78, -89}, {-69, -50, -73, -74}, {-68, -81, -79, -85}, {-70, -62, -62, -58}, {-84, -70, -57, -73}, {-70, -63, -63, -72}, {-71, -65, -64, -74}, {-69, -64, -64, -71}, {-71, -62, -62, -58}, {-70, -71, -60, -61}, {-67, -68, -64, -71}, {-64, -50, -74, -73}, {-66, -69, -69, -52}, {-70, -75, -84, -89}, {-67, -68, -65, -52}, {-63, -65, -75, -50}, {-68, -50, -74, -72}, {-83, -70, -53, -75}, {-67, -67, -64, -80}, {-84, -70, -53, -74}, {-65, -68, -64, -72}, {-66, -67, -66, -71}, {-67, -50, -72, -69}, {-64, -50, -72, -73}, {-67, -67, -72, -71}, {-65, -68, -66, -71}, {-70, -63, -63, -77}, {-66, -51, -71, -74}, {-68, -65, -67, -80}, {-83, -70, -56, -74}, {-66, -64, -67, -80}, {-64, -68, -71, -69}, {-69, -64, -64, -76}, {-67, -76, -80, -89}, {-65, -51, -71, -75}, {-89, -70, -55, -74}, {-67, -62, -67, -66}, {-65, -68, -71, -67}, {-66, -64, -64, -80}, {-67, -64, -68, -80}, {-66, -81, -82, -83}, {-70, -71, -62, -60}, {-69, -65, -62, -74}, {-84, -68, -51, -74}, {-69, -64, -68, -66}, {-68, -80, -81, -88}, {-66, -67, -67, -52}, {-84, -70, -56, -75}, {-71, -60, -71, -67}, {-73, -71, -59, -63}, {-68, -50, -77, -72}, {-71, -65, -65, -72}, {-69, -63, -69, -67}, {-67, -66, -73, -69}, {-66, -64, -68, -74}, {-66, -68, -73, -71}, {-66, -50, -75, -71}, {-72, -73, -61, -61}, {-72, -68, -62, -63}, {-67, -81, -82, -84}, {-69, -61, -71, -68}, {-73, -69, -69, -68}, {-70, -65, -64, -73}, {-66, -69, -71, -68}, {-66, -79, -81, -84}, {-66, -64, -68, -74}, {-67, -68, -65, -52}, {-71, -62, -60, -58}, {-64, -68, -68, -52}, {-69, -65, -73, -67}, {-68, -63, -63, -77}, {-68, -62, -73, -66}, {-69, -61, -73, -66}, {-65, -69, -67, -51}, {-63, -66, -73, -49}, {-66, -64, -65, -74}, {-69, -65, -63, -74}, {-68, -51, -72, -74}, {-65, -66, -67, -71}, {-70, -76, -85, -86}, {-71, -61, -63, -58}, {-69, -63, -60, -58}, {-67, -50, -71, -70}, {-67, -67, -66, -72}, {-69, -62, -71, -68}, {-65, -51, -71, -69}, {-81, -70, -54, -73}, {-83, -68, -51, -76}, {-67, -68, -65, -71}, {-66, -64, -69, -75}, {-73, -70, -73, -66}, {-68, -63, -63, -73}, {-86, -68, -52, -72}, {-67, -64, -65, -71}, {-63, -68, -68, -50}, {-71, -65, -64, -77}, {-65, -51, -73, -72}, {-71, -65, -65, -74}, {-70, -77, -81, -86}, {-67, -64, -69, -75}, {-67, -51, -73, -74}, {-82, -70, -56, -75}, {-65, -70, -65, -52}, {-70, -80, -80, -88}, {-64, -65, -73, -49}, {-68, -65, -62, -70}, {-69, -63, -62, -74}, {-64, -51, -72, -69}, {-70, -64, -67, -68}, {-66, -71, -69, -52}, {-70, -71, -59, -58}, {-84, -69, -53, -74}, {-66, -76, -80, -89}, {-70, -81, -80, -87}, {-71, -70, -62, -62}, {-66, -65, -73, -68}, {-66, -66, -73, -69}, {-67, -68, -64, -79}, {-72, -62, -73, -69}, {-70, -66, -67, -68}, {-65, -67, -71, -70}, {-64, -65, -71, -67}, {-64, -49, -74, -72}, {-70, -62, -61, -58}, {-67, -67, -72, -68}, {-71, -72, -62, -61}, {-67, -64, -69, -78}, {-65, -51, -75, -73}, {-66, -78, -80, -84}, {-68, -63, -70, -68}, {-70, -67, -67, -68}, {-71, -62, -68, -66}, {-67, -67, -64, -79}, {-65, -66, -73, -71}, {-67, -66, -71, -69}, {-64, -51, -71, -69}, {-68, -62, -70, -68}, {-69, -78, -78, -87}, {-68, -67, -73, -69}, {-67, -69, -65, -52}, {-73, -63, -67, -68}, {-69, -63, -62, -74}, {-68, -69, -72, -68}, {-83, -71, -57, -74}, {-67, -68, -66, -51}, {-64, -67, -69, -50}, {-65, -67, -65, -72}, {-62, -65, -74, -49}, {-68, -50, -74, -72}, {-67, -64, -66, -75}, {-65, -65, -68, -49}, {-70, -79, -80, -87}, {-67, -66, -73, -70}, {-68, -67, -73, -68}, {-63, -67, -68, -50}, {-66, -51, -77, -71}, {-65, -65, -73, -49}, {-83, -71, -56, -74}, {-74, -70, -69, -69}, {-65, -51, -72, -70}, {-85, -68, -51, -76}, {-71, -73, -60, -62}, {-69, -76, -78, -89}, {-65, -68, -71, -71}, {-67, -67, -72, -70}, {-71, -64, -62, -76}, {-63, -67, -66, -50}, {-66, -64, -68, -78}, {-69, -63, -62, -75}, {-69, -62, -60, -58}, {-71, -60, -70, -66}, {-65, -68, -73, -70}, {-66, -49, -73, -72}, {-65, -66, -76, -49}, {-70, -62, -59, -59}, {-67, -63, -64, -71}, {-82, -70, -53, -76}, {-68, -66, -67, -72}, {-68, -66, -71, -71}, {-71, -62, -61, -58}, {-64, -67, -66, -52}, {-72, -66, -67, -69}, {-71, -66, -66, -73}, {-73, -71, -62, -62}, {-65, -68, -71, -68}, {-69, -79, -79, -87}, {-82, -70, -56, -74}, {-84, -69, -51, -76}, {-68, -78, -80, -89}, {-65, -50, -71, -74}, {-65, -67, -73, -69}, {-71, -64, -63, -72}, {-71, -62, -72, -68}, {-66, -51, -73, -73}, {-65, -67, -72, -69}, {-68, -67, -66, -71}, {-63, -51, -71, -73}, {-82, -70, -56, -75}, {-84, -68, -54, -74}, {-82, -72, -52, -75}, {-70, -73, -60, -63}, {-67, -64, -64, -71}, {-86, -69, -52, -76}, {-84, -70, -56, -75}, {-70, -75, -83, -88}, {-67, -60, -70, -68}, {-67, -67, -64, -71}, {-69, -62, -62, -58}, {-69, -64, -63, -73}, {-89, -71, -51, -75}, {-65, -50, -73, -73}, {-67, -76, -83, -86}, {-70, -66, -63, -75}, {-72, -68, -61, -62}, {-70, -75, -80, -89}, {-87, -70, -51, -71}, {-63, -66, -67, -50}, {-70, -65, -65, -73}, {-82, -69, -54, -74}, {-62, -66, -69, -50}, {-63, -50, -72, -72}, {-69, -50, -73, -71}, {-65, -49, -73, -72}, {-64, -66, -66, -49}, {-82, -69, -52, -74}, {-69, -78, -80, -86}, {-66, -64, -67, -80}, {-64, -67, -66, -52}, {-69, -65, -65, -74}, {-72, -62, -63, -58}, {-73, -60, -69, -66}, {-67, -82, -80, -84}, {-65, -68, -67, -71}, {-67, -66, -71, -69}, {-73, -62, -72, -70}, {-68, -65, -69, -79}, {-66, -51, -72, -72}, {-89, -71, -55, -71}, {-64, -51, -74, -73}, {-66, -78, -82, -84}, {-70, -64, -63, -75}, {-82, -69, -52, -76}, {-68, -82, -81, -84}, {-64, -68, -71, -67}, {-66, -64, -69, -78}, {-71, -65, -62, -77}, {-83, -70, -56, -75}, {-69, -64, -63, -75}};
        int[] y = {6, 0, 1, 1, 8, 7, 5, 4, 4, 7, 4, 3, 4, 7, 8, 8, 5, 1, 1, 4, 2, 6, 4, 0, 7, 1, 3, 3, 3, 4, 4, 0, 1, 8, 6, 8, 1, 7, 0, 8, 5, 2, 6, 8, 7, 4, 2, 7, 3, 5, 8, 0, 0, 7, 4, 0, 2, 0, 7, 6, 4, 4, 4, 7, 7, 5, 2, 8, 0, 8, 8, 2, 6, 5, 6, 5, 5, 2, 2, 3, 5, 4, 2, 5, 6, 5, 3, 4, 0, 2, 6, 1, 3, 5, 5, 0, 7, 4, 6, 1, 0, 8, 6, 1, 7, 2, 4, 1, 3, 5, 3, 2, 7, 7, 0, 1, 1, 4, 3, 0, 5, 8, 7, 8, 1, 4, 1, 1, 8, 8, 5, 4, 2, 5, 0, 7, 7, 2, 5, 1, 2, 6, 6, 5, 5, 1, 4, 6, 5, 8, 4, 2, 4, 0, 5, 2, 6, 8, 0, 8, 4, 4, 2, 1, 8, 7, 6, 0, 0, 7, 3, 3, 5, 1, 1, 3, 3, 2, 7, 3, 7, 5, 2, 0, 1, 1, 1, 5, 3, 3, 2, 1, 0, 3, 8, 1, 4, 3, 6, 8, 8, 5, 8, 2, 5, 8, 0, 3, 3, 8, 2, 8, 6, 1, 2, 6, 7, 0, 3, 3, 4, 8, 5, 4, 7, 1, 3, 2, 8, 7, 4, 6, 5, 3, 7, 8, 1, 4, 7, 3, 0, 6, 6, 0, 2, 3, 4, 1, 2, 3, 5, 2, 6, 6, 6, 7, 4, 6, 6, 0, 1, 5, 7, 4, 6, 2, 0, 4, 7, 0, 6, 8, 4, 6, 8, 2, 2, 2, 8, 6, 0, 5, 8, 4, 7, 1, 0, 5, 3, 1, 5, 2, 6, 2, 0, 4, 6, 0, 3, 5, 4, 6, 4};
        KNeighborsClassifier clf = new KNeighborsClassifier(9, 9, 2, X, y);
        return clf;
    }

    private int nNeighbors;
    private int nTemplates;
    private int nClasses;
    private double power;
    private double[][] X;
    private int[] y;

    private KNeighborsClassifier(int nNeighbors, int nClasses, double power, double[][] X, int[] y) {
        this.nNeighbors = nNeighbors;
        this.nTemplates = y.length;
        this.nClasses = nClasses;
        this.power = power;
        this.X = X;
        this.y = y;
    }

    private static class Neighbor {
        Integer clazz;
        Double dist;
        public Neighbor(int clazz, double dist) {
            this.clazz = clazz;
            this.dist = dist;
        }
    }



    private static double compute(double[] temp, double[] cand, double q) {
        double dist = 0.;
        double diff;
        for (int i = 0, l = temp.length; i < l; i++) {
            diff = Math.abs(temp[i] - cand[i]);
            if (q==1) {
                dist += diff;
            } else if (q==2) {
                dist += diff*diff;
            } else if (q==Double.POSITIVE_INFINITY) {
                if (diff > dist) {
                    dist = diff;
                }
            } else {
                dist += Math.pow(diff, q);
            }
        }
        if (q==1 || q==Double.POSITIVE_INFINITY) {
            return dist;
        } else if (q==2) {
            return Math.sqrt(dist);
        } else {
            return Math.pow(dist, 1. / q);
        }
    }

    public Integer predict(double[] features) {
        int classIdx = 0;
        if (this.nNeighbors == 1) {
            double minDist = Double.POSITIVE_INFINITY;
            double curDist;
            for (int i = 0; i < this.nTemplates; i++) {
                curDist = KNeighborsClassifier.compute(this.X[i], features, this.power);
                if (curDist <= minDist) {
                    minDist = curDist;
                    classIdx = y[i];
                }
            }
        } else {
            int[] classes = new int[this.nClasses];
            ArrayList<Neighbor> dists = new ArrayList<Neighbor>();
            for (int i = 0; i < this.nTemplates; i++) {
                dists.add(new Neighbor(y[i], KNeighborsClassifier.compute(this.X[i], features, this.power)));
            }
            Collections.sort(dists, new Comparator<Neighbor>() {
                @Override
                public int compare(Neighbor n1, Neighbor n2) {
                    return n1.dist.compareTo(n2.dist);
                }
            });
            for (Neighbor neighbor : dists.subList(0, this.nNeighbors)) {
                classes[neighbor.clazz]++;
            }
            for (int i = 0; i < this.nClasses; i++) {
                classIdx = classes[i] > classes[classIdx] ? i : classIdx;
            }
        }
        return classIdx;
    }
}
