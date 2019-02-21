(ns twenty48.core
  (:gen-class))

(def remove-zeros (partial remove zero?))

(def sum-up-colls (partial map (partial apply +)))

(def prepend-zeros (comp (partial take-last 4) flatten reverse
                         (partial conj (repeat 4 0))))

(def partition-doubles (comp (partial mapcat identity) 
                             (partial map (partial partition-all 2))
                             (partial partition-by identity)))

(def move-row-right (comp prepend-zeros 
                          reverse flatten
                          sum-up-colls
                          partition-doubles
                          remove-zeros
                          reverse))

(def move-grid-right
  "Moves an entire grid to the right"
  (partial map move-row-right))

(defn move-grid-left
  "Moves an entire grid to the left"
  [grid]
  grid)

(defn move-grid-down
  "Moves an entire grid down"
  [grid]
  grid)

(defn move-grid-up
  "Moves an entire grid up"
  [grid]
  grid)
