(ns twenty48.core-test
  (:require [clojure.test :refer :all]
            [twenty48.core :refer :all]))

(deftest moving-grid-right
  (testing "should remove all zeros from given collection"
    (let [input-coll [1 0 0 1 2 3 0 0 4]
          expected-coll [1 1 2 3 4]]
      (is (= (remove-zeros input-coll) expected-coll))))

  (testing "should give the given collection back if there is no zero"
    (let [input-coll [1 1 2 3 4]
          expected-coll [1 1 2 3 4]]
      (is (= (remove-zeros input-coll) expected-coll))))

  (testing "should sum up each coll in the given collection"
    (let [input-coll [[2 2] [4] [2]]
          expected-coll [4 4 2]]
      (is (= (sum-up-colls input-coll) expected-coll))))

  (testing "should prepend two zeros to given collection for count being 4"
    (let [input-coll [2 4]
          expected-coll [0 0 2 4]]
      (is (= (prepend-zeros input-coll) expected-coll))))

  (testing "should prepend 4 zeros to given empty collection for count being 4"
    (let [input-coll []
          expected-coll [0 0 0 0]]
      (is (= (prepend-zeros input-coll) expected-coll))))

  (testing "should partition all consequent twice occuring items in given coll: odd occurance case"
    (let [input-coll [0 0 2 2 2 0]
          expected-coll [[0 0] [2 2] [2] [0]]]
      (is (= (partition-doubles input-coll) expected-coll))))

  (testing "should partition all consequent twice occuring items in given coll: even occurance case"
    (let [input-coll [0 2 2 2 2 0]
          expected-coll [[0] [2 2] [2 2] [0]]]
      (is (= (partition-doubles input-coll) expected-coll))))

  (testing "should partition all items for zero consequent occurance in given coll"
    (let [input-coll [2 0 2 0 4 0 4]
          expected-coll [[2] [0] [2] [0] [4] [0] [4]]]
      (is (= (partition-doubles input-coll) expected-coll))))

  (testing "should add consequent twice repeating numbers from right to left and give a 4 sized collection"
    (let [input-coll [2 2 4 4]
          expected-coll [0 0 4 8]]
      (is (= (move-row-right input-coll) expected-coll))))

  (testing "should add next set of twice repeating numbers as separate set"
    (let [input-coll [2 2 2 2]
          expected-coll [0 0 4 4]]
      (is (= (move-row-right input-coll) expected-coll))))

  (testing "should not consider twice occuring from left to right but otherway round"
    (let [input-coll [4 2 2 2]
          expected-coll [0 4 2 4]]
      (is (= (move-row-right input-coll) expected-coll))))

  (testing "should not consider thrice occuring in one set"
    (let [input-coll [2 2 2 4]
          expected-coll [0 2 4 4]]
      (is (= (move-row-right input-coll) expected-coll))))

  (testing "rows with numbers that repeat"
    (let [expected-grid '((0 0 0 4)
                          (0 0 2 4)
                          (0 0 0 4)
                          (0 0 0 8))
          input-grid '((0 0 2 2)
                       (0 2 0 4)
                       (2 0 2 0)
                       (0 4 4 0))]
      (is (= expected-grid (move-grid-right input-grid)))))

  (testing "rows with numbers that doesn't repeat consequently"
    (let [expected-grid '((4 2 4 2)
                          (0 0 2 4)
                          (0 0 0 0)
                          (0 4 2 8))
          input-grid '((4 2 4 2)
                       (0 2 0 4)
                       (0 0 0 0)
                       (0 4 2 8))]
      (is (= expected-grid (move-grid-right input-grid))))))

(deftest moving-grid-left
  (testing "rows with numbers that repeat"
    (is (= '((4 0 0 0)
             (2 4 0 0)
             (4 0 0 0)
             (8 0 0 0))
           (move-grid-left
            '((0 0 2 2)
              (0 2 0 4)
              (2 0 2 0)
              (0 4 4 0)))))))

(deftest moving-grid-up
  (testing "rows with numbers that repeat"
    (is (= '((2 2 4 2)
             (0 4 4 4)
             (0 0 0 0)
             (0 0 0 0))
           (move-grid-up
            '((0 0 2 2)
              (0 2 0 4)
              (2 0 2 0)
              (0 4 4 0)))))))

(deftest moving-grid-down
  (testing "rows with numbers that repeat"
    (is (= '((0 0 0 0)
             (0 0 0 0)
             (0 2 4 2)
             (2 4 4 4))
           (move-grid-down
            '((0 0 2 2)
              (0 2 0 4)
              (2 0 2 0)
              (0 4 4 0)))))))
