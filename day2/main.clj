(ns advent2019.day2)

(def opcodes {1 'add, 
              2 'mult,
              99 'exit})

(defn do-opcode [memory src_a src_b dst oper]
  (assoc memory dst (oper 
                    (get memory src_a)
                    (get memory src_b))))

(defn add [memory src_a src_b dst]
  (do-opcode memory src_a src_b dst +))

(defn mult [memory src_a src_b dst]
  (do-opcode memory src_a src_b dst *))

(defn exit [& _]
  nil)

(defn read-opcode [memory pos] 
  [ (get memory (+ 1 pos))
    (get memory (+ 2 pos))
    (get memory (+ 3 pos)) ])

(defn execute-opcode [memory pos]
  (let [code (get memory pos)
        instruction (resolve (symbol (opcodes code)))
        [src_a src_b dst] (read-opcode memory pos)]
    (if-not instruction
      (do
        (println (str "Error! Invalid opcode: got " code)) 
        (System/exit 1))
    (do
      ;; (println (str instruction ": " src_a " and " src_b " -> " dst))
      ;; (println (instruction memory src_a src_b dst))
      (instruction memory src_a src_b dst)))))
    
(defn run-seq [memory pos]
   (lazy-seq
    (let [memory' (execute-opcode memory pos)]
      (cons memory' (run-seq memory' (+ pos 4))))))

(defn run [memory]
  (last (take-while (comp not nil?) (run-seq memory 0))))

(defn restart-1202 [memory]
  (assoc memory 1 12
                2 2))

(def input
  (-> (slurp "input")
      (clojure.string/split #",")
      (->> (mapv #(clojure.string/trim %))
           (mapv #(Integer/parseInt %)))))

;; main
(println "Part 1:")
(println (-> input
            (restart-1202)
            (run)
            (get 0)))
