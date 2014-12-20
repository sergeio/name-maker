(ns example.hello
  (:require [example.names :as names]
            ;[clojure.browser.dom :as dom]))
            [goog.dom :as dom]))

(def guy-names '("Michael", "Jacob"))
(def guy-names names/guy-names)

;(js/alert names.guy-names)
;(.log js/console "hi")
;(.log js/console names/guy-names)

(defn member? [target collection]
  "Is the target a member of the collection?"
  (cond
    (empty? collection) false
    (= target (first collection)) true
    :else (recur target (rest collection))))

(defn get-letter-after [letter collection]
  "Return the element in the collection following the letter specified.
  Assumes letter is present in the collection."
  (if (= letter (first collection))
    (if (empty? (rest collection)) "$" (second collection))
    (recur letter (rest collection))))

(defn string->seq [string] (re-seq #"." string))

(defn get-names-containing [letter guy-names]
  (filter #(member? letter (string->seq %)) guy-names))

;(.log js/console (rand-nth (get-names-containing "J" guy-names)))

(defn vector->name [letter-vector]
  (reduce str (reverse (rest (reverse letter-vector)))))

(defn append-another-letter [letter-vector guy-names]
  (let [last-letter (last letter-vector)]
    (conj letter-vector
          (get-letter-after last-letter
                            (rand-nth (get-names-containing last-letter guy-names))))))

(defn build-name []
  (let [first-letter (first (rand-nth guy-names))]
    (loop [letter-vector (vector first-letter)]
      (if (= "$" (last letter-vector))
        (vector->name letter-vector)
        (recur (append-another-letter letter-vector guy-names))))))

;http://docs.closure-library.googlecode.com/git/namespace_goog_dom.html

(dom/setTextContent (dom/getElementByClass "header") (build-name))
;(.log js/console (dom/getElementByClass "header"))
;(dom/getElementByClassName "w")

;(dom/append "woo")
;(let [div (.createElement js/document "DIV")]
;  (.appendChild div (.createElement js/document "input")))

;(.log ks/console (reduce str (build-name [])))
(.log js/console (build-name))
;(.log js/console (reduce str (take 2 (rand-nth guy-names))))
