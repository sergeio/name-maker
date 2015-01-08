(ns example.hello
  (:require [example.names :as names]
            [goog.dom :as dom]))

(def guy-names names/guy-names)

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
  "Get all the names that contain the desired letter."
  (filter #(member? letter (string->seq %)) guy-names))

(defn vector->name [letter-vector]
  "Turn vector of names into a string, dropping the last '$' character."
  (reduce str (reverse (rest (reverse letter-vector)))))

(defn append-another-letter [letter-vector guy-names]
  "Add a letter to the `letter-vector`."
  (let [last-letter (last letter-vector)]
    (conj letter-vector
          (get-letter-after
            last-letter
            (rand-nth (get-names-containing last-letter guy-names))))))

(defn build-name []
  "Create a name that has structure similar to `guy-names`."
  (let [first-letter (first (rand-nth guy-names))]
    (loop [letter-vector (vector first-letter)]
      (if (= "$" (last letter-vector))
        (vector->name letter-vector)
        (recur (append-another-letter letter-vector guy-names))))))

;http://docs.closure-library.googlecode.com/git/namespace_goog_dom.html
(dom/setTextContent (dom/getElementByClass "header") (build-name))
