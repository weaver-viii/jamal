(ns app.main
  (:require
   cljsjs.react
   cljsjs.moment
   [clojure.browser.repl :as repl]))

(repl/connect "http://localhost:9000/repl")

(enable-console-print!)

(def app-state
  (atom
   {:status :alive
    :born (.fromNow (js/moment "1981-07-01"))}))

(def root (.getElementById js/document "root"))

(defn init []
  (println "Hello world!")
  (.render
   js/React
   (.createElement js/React "h1" nil "Fuck yeah")
   root))
