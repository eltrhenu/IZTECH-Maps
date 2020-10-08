package business;

public class CalculateDistance {
    private final static int DEFAULT_DISTANCE = 6;
    /**
     * Calculate distance by checking categoryType
     * @param from the source location that users give for starting point
     * @param to the source location that users give for final point
     * @return calculated distance
     */
    public double calculateDistance(Node from, Node to) {
        if (from.getType().equals(CategoryType.Department)) {
            if (to.getType().equals(CategoryType.Cafeteria)) {
                return ((DEFAULT_DISTANCE * 2) - 3);

            } else if (to.getType().equals(CategoryType.Beach)) {
                return ((DEFAULT_DISTANCE * DEFAULT_DISTANCE) / 2) + 4;
            } else if (to.getType().equals(CategoryType.Administrative)) {
                return DEFAULT_DISTANCE / 2;
            }
        } else if (from.getType().equals(CategoryType.Administrative)) {
            if (to.getType().equals(CategoryType.Department)) {
                return DEFAULT_DISTANCE / 2;
            }
        } else if (from.getType().equals(CategoryType.Cafeteria)) {
            if (to.getType().equals(CategoryType.Department)) {
                return ((DEFAULT_DISTANCE * 2) - 3);

            } else if (to.getType().equals(CategoryType.WaterFall)) {
                return DEFAULT_DISTANCE / 3;
            } else if (to.getType().equals(CategoryType.HistoricalRuin)) {
                return DEFAULT_DISTANCE * DEFAULT_DISTANCE;
            } else if (to.getType().equals(CategoryType.Facility)) {
                return Math.sqrt(DEFAULT_DISTANCE);
            }
        } else if (from.getType().equals(CategoryType.WaterFall)) {
            if (to.getType().equals(CategoryType.Cafeteria)) {
                return DEFAULT_DISTANCE / 3;
            } else if (to.getType().equals(CategoryType.Facility)) {
                return (DEFAULT_DISTANCE * 5) / 2;
            }

        } else if (from.getType().equals(CategoryType.Facility)) {
            if (to.getType().equals(CategoryType.WaterFall)) {
                return (DEFAULT_DISTANCE * 5) / 2;
            } else if (to.getType().equals(CategoryType.Cafeteria)) {
                return Math.sqrt(DEFAULT_DISTANCE);
            }
        } else if (from.getType().equals(CategoryType.HistoricalRuin)) {
            if (to.getType().equals(CategoryType.Cafeteria)) {
                return DEFAULT_DISTANCE * DEFAULT_DISTANCE;
            }
        } else if (from.getType().equals(CategoryType.Beach)) {
            if (to.getType().equals(CategoryType.Department)) {
                return (DEFAULT_DISTANCE * DEFAULT_DISTANCE / 2) + 4;
            }
        }

        return DEFAULT_DISTANCE;




    }




}
