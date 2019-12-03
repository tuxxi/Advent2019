(ns advent2019.day1)

(defn parse [filename]
  (mapv #(Integer/parseInt %) 
        (clojure.string/split-lines (slurp filename))))

(defn calc-fuel [mass]
  (-> mass (/ 3) (int) (- 2))) 

(defn calc-total-fuel [mass]
  (apply + (take-while pos? (iterate calc-fuel (calc-fuel mass)))))

(defn day1-1 [filename]
    (apply + (mapv calc-fuel (parse filename))))

(defn day1-2 [filename]
    (apply + (mapv calc-total-fuel (parse filename))))

;; main
(println "Day 1, Part 1:")
(println (day1-1 "day1.input"))
(println "Part 2:")
(println (day1-2 "day1.input"))
