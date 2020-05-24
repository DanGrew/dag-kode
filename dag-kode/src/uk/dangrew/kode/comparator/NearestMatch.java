package uk.dangrew.kode.comparator;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

public class NearestMatch {

    /**
     * Finds the nearest match given conditions.
     * @param objects the objects to search through.
     * @param thresholdFunction the threshold to determine whether the given pair surround the match.
     * @param deciderFunction the function to determine whether of the two matches is closer.
     * @param <ObjectT> the type of object being searched.
     * @return Optional of the match.
     */
    public <ObjectT> Optional<ObjectT> findNearest(
            List<ObjectT> objects,
            BiFunction<ObjectT, ObjectT, Boolean> thresholdFunction,
            BiFunction<ObjectT, ObjectT, ObjectT> deciderFunction
    ){
        for (int i = 1; i < objects.size(); i++) {
            ObjectT previous = objects.get(i-1);
            ObjectT current = objects.get((i));

            if ( !thresholdFunction.apply(previous, current)){
                continue;
            }

            return Optional.of(deciderFunction.apply(previous, current));
        }
        return Optional.empty();
    }

}
