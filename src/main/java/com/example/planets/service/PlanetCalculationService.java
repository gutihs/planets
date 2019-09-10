package com.example.planets.service;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.logging.Logger;

@Component
public class PlanetCalculationService {

    Logger log = Logger.getLogger(this.getClass().getName());

    public boolean areAligned(double w1t, double w2t, double w3t, int r1, int r2, int r3) {
        DecimalFormat df = new DecimalFormat("0.00");

        double firstPart = r1 * r2 * Double.parseDouble(df.format(Math.sin(Math.toRadians(w2t-w1t))));
        log.info("firstPart: " + firstPart);

        double secondPart = r2 * r3 * Double.parseDouble(df.format(Math.sin(Math.toRadians(w3t+w2t))));
        log.info("secondPart: " + secondPart);

        double thirdPart = r3 * r1 * Double.parseDouble(df.format(Math.sin(Math.toRadians(w1t+w3t))));
        log.info("thirdPart: " + thirdPart);

        double result = firstPart + secondPart + thirdPart;
        log.info("result: " + result);

        return result == 0;
    }

    public boolean areAlignedWithSun(double w1t, double w2t, double w3t) {
        boolean result = false;

        double deg1 = w1t <= 360.0 ? w1t : w1t % 360;
        log.info("deg1: " + deg1);
        double deg2 = w2t <= 360.0 ? w2t : w2t % 360;
        log.info("deg2: " + deg2);
        double deg3 = w3t <= 360.0 ? (360 - w3t) : (360 - (w3t % 360));
        log.info("deg3: " + deg3);

        if((deg1 == deg2) && (deg1 == deg3) && (deg2 == deg3)) {
            result = true;
        } else {

            if (deg1 != deg2) {
                if (deg1 <= 180) {
                    result = (deg1 + 180) == deg2;
                } else {
                    result = (deg1 - 180) == deg2;
                }
            } else if (deg1 != deg3) {
                if (deg2 <= 180) {
                    result = (deg1 + 180) == deg3;
                } else {
                    result = (deg1 - 180) == deg3;
                }
            } else if (deg2 != deg3) {
                if (deg2 <= 180) {
                    result = (deg2 + 180) == deg3;
                } else {
                    result = (deg2 - 180) == deg3;
                }
            }
        }

        return result;
    }

    public boolean isSunInside(double w1t, double w2t, double w3t, int r1, int r2, int r3) {
        DecimalFormat df = new DecimalFormat("0.00");
        int count = 0;

        double x1 = r1 * Math.cos(Math.toRadians(w1t));
        double y1 = r1 * Math.sin(Math.toRadians(w1t));

        double x2 = r2 * Math.cos(Math.toRadians(w2t));
        double y2 = r2 * Math.sin(Math.toRadians(w2t));

        double x3 = r3 * Math.cos(Math.toRadians(-w3t));
        double y3 = r3 * Math.sin(Math.toRadians(-w3t));

        double apHeight = calculateTriangleHeight(x1, y1, x2, y2, x3, y3);
        double bpHeight = calculateTriangleHeight(x2, y2, x1, y1, x3, y3);
        double cpHeight = calculateTriangleHeight(x3, y3, x1, y1, x2, y2);

        if (apHeight > r1) {
            count++;
        }
        if (bpHeight > r2 ) {
            count++;
        }
        if (cpHeight > r3) {
            count++;
        }

        return count >= 2;
    }

    public double calculateTrianglePerimeter(double w1t, double w2t, double w3t, int r1, int r2, int r3) {

        double x1 = r1 * Math.cos(Math.toRadians(w1t));
        double y1 = r1 * Math.sin(Math.toRadians(w1t));

        double x2 = r2 * Math.cos(Math.toRadians(w2t));
        double y2 = r2 * Math.sin(Math.toRadians(w2t));

        double x3 = r3 * Math.cos(Math.toRadians(-w3t));
        double y3 = r3 * Math.sin(Math.toRadians(-w3t));

        double d12 = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        double d23 = Math.sqrt(Math.pow((x3 - x2), 2) + Math.pow((y3 - y2), 2));
        double d31 = Math.sqrt(Math.pow((x1 - x3), 2) + Math.pow((y1 - y3), 2));

        double perimeter = d12 + d23 + d31;

        return perimeter;
    }

    private double calculateTriangleHeight(double ax, double ay, double bx, double by, double cx, double cy) {
        double m = (cy - by) / (cx - bx);

        double A = m;
        double B = -1;
        double C = ((-m)*cx) + cy;
        double X1 = ax;
        double Y1 = ay;

        double d = Math.abs((A*X1) + (B*Y1) + (C)) / Math.sqrt(Math.pow(A, 2) + Math.pow(B, 2));

        return d;
    }

}
