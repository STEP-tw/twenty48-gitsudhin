(ns twenty48.core
  (:gen-class))

(def partition-doubles (comp (partial mapcat identity)
                             (partial map (partial partition-all 2))
                             (partial partition-by identity)))

(def remove-zeros (partial remove zero?))

(def sum-up-colls (partial map (partial apply +)))

(def prepend-zeros (comp (partial take-last 4) (partial concat (repeat 4 0))))

(def append-zeros (comp (partial take 4) flatten (partial conj (repeat 4 0))))

(def add-repeating-nums (comp flatten sum-up-colls partition-doubles))

(def move-row-right (comp prepend-zeros
                          reverse
                          add-repeating-nums remove-zeros
                          reverse))

(def move-row-left (comp append-zeros
                         add-repeating-nums
                         remove-zeros))

(def move-grid-right
  "Moves an entire grid to the right"
  (partial map move-row-right))

(def move-grid-left
  "Moves an entire grid to the left"
  (partial map move-row-left))

(defn move-grid-down
  "Moves an entire grid down"
  [grid]
  grid)

(defn move-grid-up
  "Moves an entire grid up"
  [grid]
  grid)
