package com.github.angelikaowczarek;

import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.RealPointValuePair;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.LinearObjectiveFunction;
import org.apache.commons.math.optimization.linear.Relationship;
import org.apache.commons.math.optimization.linear.SimplexSolver;

import java.util.*;

public class Optimizer {

    private double[] results;

    public void findMin(List<Cut> cuts, int[] desiredQuantites) {

        results = new double[cuts.size()];
        LinearObjectiveFunction function = getLinearObjectiveFunction(cuts);
        List<LinearConstraint> constraints = getConstraints(cuts, desiredQuantites);
        optimizeSolution(function, constraints);
    }

    private void optimizeSolution(
            LinearObjectiveFunction function,
            List<LinearConstraint> constraints) {

        RealPointValuePair solution = null;
        try {
            solution = new SimplexSolver().optimize(function, constraints, GoalType.MINIMIZE, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (solution != null) {
            for (int i = 0; i < results.length; i++) {
                results[i] = solution.getPoint()[i];
            }
        }
    }

    private LinearObjectiveFunction getLinearObjectiveFunction(List<Cut> cuts) {
        double[] culls = new double[cuts.size()];
        for (int i = 0; i < culls.length; i++) {
            culls[i] = cuts.get(1).getCull();
        }

        return new LinearObjectiveFunction(culls, 0);
    }

    private List<LinearConstraint> getConstraints(List<Cut> cuts, int[] desiredQuantites) {
        List<LinearConstraint> constraints = new ArrayList<>();

        double[] tempCuts = new double[cuts.size()];
        Arrays.fill(tempCuts, 0);

        int numberOfElements = cuts.get(0).getElementsQuantity().length;

        for (int i = 0; i < numberOfElements; i++) {
            for (int j = 0; j < tempCuts.length; j++) {
                tempCuts[j] = cuts.get(j).getElementsQuantity()[i];
            }
            constraints.add(new LinearConstraint(tempCuts, Relationship.GEQ, desiredQuantites[i]));
        }

        for (int i = 0; i < cuts.size(); i++) {
            double[] temp = new double[cuts.size()];
            temp[i] = 1;
            constraints.add(new LinearConstraint(temp, Relationship.GEQ, 0));
        }
        return constraints;
    }

    public double[] getResults() {
        return results;
    }
}
