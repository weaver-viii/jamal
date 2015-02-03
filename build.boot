(set-env!
 :source-paths    #{"src"}
 :resource-paths  #{"html" "conf"}
 :dependencies    '[[org.clojure/clojurescript "0.0-2760"]
                    [adzerk/boot-cljs   "0.0-2760-0"]
                    [pandeiro/boot-http "0.5.0"]
                    [cljsjs/react "0.12.2-5"]
                    [cljsjs/moment "2.6.0-3"]])

(require
 'clojure.main
 'cljs.repl
 'cljs.repl.browser
 '[adzerk.boot-cljs   :refer [cljs]]
 '[pandeiro.boot-http :refer [serve]])

(deftask dev []
  (comp
   (watch)
   (cljs :optimizations    :none
         :source-map       true
         :compiler-options {:cache-analysis true})
   (serve :dir "target", :port 8080)))

(deftask browser-repl
  "Adapted from Mies' script/brepl.clj"
  []
  (with-pre-wrap fileset
    (clojure.main/main "-e" "
     (require
      '[cljs.repl :as repl]
      '[cljs.repl.browser :as browser])

     (repl/repl* (browser/repl-env)
                 {:output-dir \".browser-repl\"
                  :optimizations :none
                  :cache-analysis true                
                  :source-map true})")
    fileset))
