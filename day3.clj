(ns advent2019.day3)

(defn str->ints [s]
  (->> s
      (filter #(Character/isDigit %))
      (apply str)
      (Integer/parseInt)))

(defn str->direction [s]
  (->> s
       (filter #(Character/isUpperCase %))
       (apply str)))

;; parse the input string into a list of tuples [Direction, Amt]
(defn parse [s]
  (let [cmds (clojure.string/split s #",")]

  (map vector  ; create tuples
    (mapv str->direction cmds)
    (mapv str->ints cmds))))

;; opens the file and creates a vec of input/output
(defn open-file []
  (-> (slurp "day3.input")
      (clojure.string/split #"\n")
      (->> (map parse))))


;; move from the current pos to our next one, returning the new position
;; as a tuple
(defn move [cmd pos]
  (let [letter (first cmd)
      amt (second cmd)
      movement-map { "R" (fn [amt pos] (vector (+ amt (first pos)) (second pos))) ; move amt in +x
                     "L" (fn [amt pos] (vector (- amt (first pos)) (second pos))) ; move amt in -x
                     "U" (fn [amt pos] (vector (+ amt (second pos)) (first pos))) ; move amt in +y
                     "D" (fn [amt pos] (vector (- amt (second pos)) (first pos)))}] ; move amt in -y
  ((movement-map letter) amt pos)))
  
;; assuming the origin of the wire is 0,0
;; return a list of every vertical and horizontal grid that the seq crosses
(defn trace [cmds])

;; main
;; (open-file)
