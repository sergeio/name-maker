(ns example.views
  (:require
    [hiccup
      [page :refer [html5]]
      [page :refer [include-js]]]))

(defn index-page []
  (html5
    [:body
      [:h1 {:class "header"} "Hi World"]]
    [:head
      [:title "Hello World"]
      (include-js "/js/main.js")]
    ))
