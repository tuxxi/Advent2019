(ns advent2019.day1
  (:gen-class))

(defn day1 [accum mass]
  (let [m (Integer/parseInt mass)]
    (+ accum (- (int (/ m 3)) 2 ))))

;; open the file "input" and call our day1 func on each line
(with-open [rdr (clojure.java.io/reader "input")]
  (println (reduce day1 0 (line-seq rdr))))
