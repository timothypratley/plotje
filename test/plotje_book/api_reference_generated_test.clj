(ns
 plotje-book.api-reference-generated-test
 (:require
  [scicloj.kindly.v4.kind :as kind]
  [scicloj.metamorph.ml.rdatasets :as rdatasets]
  [tablecloth.api :as tc]
  [scicloj.plotje.api :as pj]
  [fastmath.random :as rng]
  [clojure.test :refer [deftest is]]))


(def
 v3_l28
 (def tiny {:x [1 2 3 4 5], :y [2 4 1 5 3], :group [:a :a :b :b :b]}))


(def
 v4_l32
 (def
  sales
  {:product [:widget :gadget :gizmo :doohickey],
   :revenue [120 340 210 95]}))


(def
 v5_l35
 (def
  measurements
  {:treatment ["A" "B" "C" "D"],
   :mean [10.0 15.0 12.0 18.0],
   :ci-lo [8.0 12.0 9.5 15.5],
   :ci-hi [12.0 18.0 14.5 20.5]}))


(def v7_l50 (kind/doc #'pj/pose))


(def
 v9_l54
 (->
  (rdatasets/datasets-iris)
  (pj/pose :sepal-length :sepal-width)
  pj/lay-point))


(deftest
 t10_l58
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 1 (:panels s)) (= 150 (:points s)))))
   v9_l54)))


(def
 v12_l64
 (->
  (rdatasets/datasets-iris)
  (pj/pose :sepal-length :sepal-width {:color :species})
  pj/lay-point
  (pj/lay-smooth {:stat :linear-model})))


(deftest
 t13_l69
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 150 (:points s)) (= 3 (:lines s)))))
   v12_l64)))


(def v15_l77 (pj/pose [1 4 1 5 6 2 3 3 3 2 4 5 1 2 3 4]))


(deftest
 t16_l79
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 1 (:panels s)) (pos? (:polygons s)))))
   v15_l77)))


(def v17_l83 (kind/doc #'pj/with-data))


(def
 v19_l89
 (def
  scatter-template
  (-> (pj/pose nil {:x :x, :y :y, :color :group}) pj/lay-point)))


(def v20_l93 (-> scatter-template (pj/with-data tiny)))


(deftest
 t21_l96
 (is ((fn [v] (= 5 (:points (pj/svg-summary v)))) v20_l93)))


(def
 v23_l101
 (->
  (rdatasets/datasets-iris)
  (pj/arrange
   [(pj/pose nil :sepal-length :sepal-width)
    (pj/pose nil :petal-length :petal-width)])
  (pj/lay-point {:color :species})))


(deftest
 t24_l106
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 2 (:panels s)) (= 300 (:points s)))))
   v23_l101)))


(def
 v26_l112
 (->
  (rdatasets/datasets-iris)
  (pj/pose {:x :sepal-length, :y :sepal-width})
  pj/lay-point))


(deftest
 t27_l116
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 1 (:panels s)) (= 150 (:points s)))))
   v26_l112)))


(def v28_l120 (kind/doc #'pj/cross))


(def v29_l122 (pj/cross [:a :b] [1 2 3]))


(deftest
 t30_l124
 (is
  ((fn [v] (= [[:a 1] [:a 2] [:a 3] [:b 1] [:b 2] [:b 3]] v))
   v29_l122)))


(def
 v32_l128
 (->
  (rdatasets/datasets-iris)
  (pj/cross-matrix [:sepal-length :petal-length] {:color :species})))


(deftest
 t33_l132
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 4 (:panels s)) (= 600 (:points s)))))
   v32_l128)))


(def v35_l138 (kind/doc #'pj/lay))


(def
 v37_l146
 (->
  (rdatasets/datasets-iris)
  (pj/pose :sepal-length :sepal-width)
  (pj/lay :point)))


(deftest
 t38_l150
 (is ((fn [v] (= 150 (:points (pj/svg-summary v)))) v37_l146)))


(def v40_l161 (kind/doc #'pj/lay-point))


(def
 v41_l163
 (->
  (rdatasets/datasets-iris)
  (pj/lay-point :sepal-length :sepal-width {:color :species})))


(deftest
 t42_l166
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 150 (:points s)))) v41_l163)))


(def v43_l169 (kind/doc #'pj/lay-line))


(def
 v44_l171
 (def
  wave
  {:x (range 30),
   :y
   (map
    (fn* [p1__141514#] (Math/sin (* p1__141514# 0.3)))
    (range 30))}))


(def v45_l174 (-> wave (pj/lay-line :x :y)))


(deftest
 t46_l177
 (is ((fn [v] (let [s (pj/svg-summary v)] (= 1 (:lines s)))) v45_l174)))


(def v47_l180 (kind/doc #'pj/lay-histogram))


(def
 v48_l182
 (-> (rdatasets/datasets-iris) (pj/lay-histogram :sepal-length)))


(deftest
 t49_l185
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (pos? (:polygons s))))
   v48_l182)))


(def
 v51_l190
 (pj/lay-histogram
  (rdatasets/datasets-iris)
  [:sepal-length :sepal-width]))


(deftest
 t52_l192
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 2 (:panels s)) (pos? (:polygons s)))))
   v51_l190)))


(def v53_l196 (kind/doc #'pj/lay-bar))


(def v54_l198 (-> (rdatasets/datasets-iris) (pj/lay-bar :species)))


(deftest
 t55_l201
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 3 (:polygons s)))) v54_l198)))


(def
 v57_l206
 (->
  (rdatasets/palmerpenguins-penguins)
  (pj/lay-bar :island {:position :stack, :color :species})))


(deftest
 t58_l209
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (pos? (:polygons s))))
   v57_l206)))


(def
 v60_l214
 (->
  (rdatasets/palmerpenguins-penguins)
  (pj/lay-bar :island {:position :fill, :color :species})))


(deftest
 t61_l217
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (pos? (:polygons s))))
   v60_l214)))


(def v63_l223 (-> sales (pj/lay-bar :product :revenue)))


(deftest
 t64_l226
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 4 (:polygons s)))) v63_l223)))


(def v66_l232 (-> sales (pj/lay-bar :product :revenue {:stat :count})))


(deftest
 t67_l235
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (pos? (:polygons s))))
   v66_l232)))


(def
 v69_l242
 (->
  {:hour [9 10 11], :sales [3 5 4]}
  (pj/lay-bar :hour :sales {:x-type :categorical})))


(deftest
 t70_l245
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 3 (:polygons s)))) v69_l242)))


(def v72_l252 (-> sales (pj/lay-bar :revenue :product)))


(deftest
 t73_l255
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 4 (:polygons s)))) v72_l252)))


(def
 v75_l267
 (-> sales (pj/lay-bar :product :revenue {:bar-width 0.4})))


(deftest
 t76_l270
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 4 (:polygons s)))) v75_l267)))


(def
 v78_l277
 (-> {:x [1 2 3 4 5], :y [10 20 15 30 25]} (pj/lay-bar :x :y)))


(deftest
 t79_l280
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 5 (:polygons s)))) v78_l277)))


(def
 v81_l287
 (->
  {:x [1 2 3 4 5], :y [10 20 15 30 25]}
  (pj/lay-bar :x :y {:bar-width 0.3})))


(deftest
 t82_l290
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 5 (:polygons s)))) v81_l287)))


(def
 v84_l295
 (->
  {:month
   [#inst "2024-01-01T00:00:00.000-00:00"
    #inst "2024-02-01T00:00:00.000-00:00"
    #inst "2024-03-01T00:00:00.000-00:00"],
   :revenue [120 180 150]}
  (pj/lay-bar :month :revenue)))


(deftest
 t85_l299
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 3 (:polygons s)))) v84_l295)))


(def v86_l302 (kind/doc #'pj/lay-smooth))


(def
 v88_l306
 (->
  (rdatasets/datasets-iris)
  (pj/lay-point :sepal-length :sepal-width)
  (pj/lay-smooth {:stat :linear-model})))


(deftest
 t89_l310
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 150 (:points s)) (= 1 (:lines s)))))
   v88_l306)))


(def
 v90_l314
 (->
  (let
   [r (rng/rng :jdk 42) xs (vec (range 50))]
   {:x xs,
    :y
    (mapv
     (fn*
      [p1__141515#]
      (+
       (Math/sin (* p1__141515# 0.2))
       (* 0.3 (- (rng/drandom r) 0.5))))
     xs)})
  (pj/lay-point :x :y)
  (pj/lay-smooth {:bandwidth 0.2})))


(deftest
 t91_l323
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 50 (:points s)) (= 1 (:lines s)))))
   v90_l314)))


(def v92_l327 (kind/doc #'pj/lay-density))


(def
 v93_l329
 (-> (rdatasets/datasets-iris) (pj/lay-density :sepal-length)))


(deftest
 t94_l332
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 1 (:polygons s)))) v93_l329)))


(def v95_l335 (kind/doc #'pj/lay-area))


(def v96_l337 (-> wave (pj/lay-area :x :y)))


(deftest
 t97_l340
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 1 (:polygons s)))) v96_l337)))


(def
 v99_l345
 (->
  {:x (concat (range 10) (range 10) (range 10)),
   :y
   (concat
    [1 2 3 4 5 4 3 2 1 0]
    [2 2 2 3 3 3 2 2 2 2]
    [1 1 1 1 2 2 2 1 1 1]),
   :group (concat (repeat 10 "A") (repeat 10 "B") (repeat 10 "C"))}
  (pj/lay-area :x :y {:position :stack, :color :group})))


(deftest
 t100_l352
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 3 (:polygons s)))) v99_l345)))


(def v101_l355 (kind/doc #'pj/lay-text))


(def
 v102_l357
 (->
  {:x [1 2 3 4], :y [4 7 5 8], :name ["A" "B" "C" "D"]}
  (pj/lay-text :x :y {:text :name})))


(deftest
 t103_l360
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (every? (set (:texts s)) ["A" "B" "C" "D"])))
   v102_l357)))


(def v104_l363 (kind/doc #'pj/lay-label))


(def
 v105_l365
 (->
  {:x [1 2 3 4], :y [4 7 5 8], :name ["A" "B" "C" "D"]}
  (pj/lay-point :x :y {:size 5})
  (pj/lay-label {:text :name})))


(deftest
 t106_l369
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and
      (= 4 (:points s))
      (every? (set (:texts s)) ["A" "B" "C" "D"]))))
   v105_l365)))


(def v107_l373 (kind/doc #'pj/lay-boxplot))


(def
 v108_l375
 (-> (rdatasets/datasets-iris) (pj/lay-boxplot :species :sepal-width)))


(deftest
 t109_l378
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 3 (:polygons s)) (pos? (:lines s)))))
   v108_l375)))


(def v110_l382 (kind/doc #'pj/lay-violin))


(def
 v111_l384
 (-> (rdatasets/reshape2-tips) (pj/lay-violin :day :total-bill)))


(deftest
 t112_l387
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 4 (:polygons s))))
   v111_l384)))


(def v113_l390 (kind/doc #'pj/lay-errorbar))


(def
 v114_l392
 (->
  measurements
  (pj/lay-point :treatment :mean)
  (pj/lay-errorbar {:y-min :ci-lo, :y-max :ci-hi})))


(deftest
 t115_l396
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 4 (:points s)) (= 12 (:lines s)))))
   v114_l392)))


(def v116_l400 (kind/doc #'pj/lay-lollipop))


(def v117_l402 (-> sales (pj/lay-lollipop :product :revenue)))


(deftest
 t118_l405
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 4 (:points s)) (= 4 (:lines s)))))
   v117_l402)))


(def v119_l409 (kind/doc #'pj/lay-tile))


(def
 v120_l411
 (->
  (rdatasets/datasets-iris)
  (pj/lay-tile :sepal-length :sepal-width)))


(deftest
 t121_l414
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (pos? (:visible-tiles s))))
   v120_l411)))


(def v122_l417 (kind/doc #'pj/lay-density-2d))


(def
 v123_l419
 (->
  (rdatasets/datasets-iris)
  (pj/lay-density-2d :sepal-length :sepal-width)))


(deftest
 t124_l422
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (pos? (:visible-tiles s))))
   v123_l419)))


(def v125_l425 (kind/doc #'pj/lay-contour))


(def
 v126_l427
 (->
  (rdatasets/datasets-iris)
  (pj/lay-contour :sepal-length :sepal-width)))


(deftest
 t127_l430
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (pos? (:lines s)))) v126_l427)))


(def v128_l433 (kind/doc #'pj/lay-ridgeline))


(def
 v129_l435
 (->
  (rdatasets/datasets-iris)
  (pj/lay-ridgeline :species :sepal-length)))


(deftest
 t130_l438
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (pos? (:polygons s))))
   v129_l435)))


(def v131_l441 (kind/doc #'pj/lay-rug))


(def
 v132_l443
 (->
  (rdatasets/datasets-iris)
  (pj/lay-point :sepal-length :sepal-width)
  (pj/lay-rug {:side :both})))


(deftest
 t133_l447
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 300 (:lines s)))) v132_l443)))


(def v134_l450 (kind/doc #'pj/lay-step))


(def v135_l452 (-> tiny (pj/lay-step :x :y) pj/lay-point))


(deftest
 t136_l456
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 5 (:points s)) (= 1 (:lines s)))))
   v135_l452)))


(def v137_l460 (kind/doc #'pj/lay-summary))


(def
 v138_l462
 (-> (rdatasets/datasets-iris) (pj/lay-summary :species :sepal-length)))


(deftest
 t139_l465
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 3 (:points s)) (= 3 (:lines s)))))
   v138_l462)))


(def v140_l469 (kind/doc #'pj/lay-interval-h))


(def
 v141_l471
 (->
  {:start
   [#inst "2024-01-01T00:00:00.000-00:00"
    #inst "2024-03-01T00:00:00.000-00:00"
    #inst "2024-05-01T00:00:00.000-00:00"],
   :end
   [#inst "2024-04-01T00:00:00.000-00:00"
    #inst "2024-06-01T00:00:00.000-00:00"
    #inst "2024-08-01T00:00:00.000-00:00"],
   :task ["Design" "Build" "Test"]}
  (pj/lay-interval-h :start :task {:x-end :end})))


(deftest
 t142_l476
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 3 (:polygons s))))
   v141_l471)))


(def v143_l479 (kind/doc #'pj/lay-segment))


(def
 v144_l481
 (->
  {:x0 [1 2 4], :y0 [1 3 2], :x1 [3 3 5], :y1 [2 1 4]}
  (pj/lay-segment :x0 :y0 {:x-end :x1, :y-end :y1, :arrow :end})))


(deftest
 t145_l484
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 3 (:lines s)) (= 3 (:polygons s)))))
   v144_l481)))


(def
 v147_l543
 (try
  (->
   measurements
   (pj/lay-point :treatment :mean)
   (pj/lay-errorbar {:y-min 0.5, :y-max 1.5})
   pj/plan)
  (catch Exception e (ex-message e))))


(deftest
 t148_l549
 (is
  ((fn
    [msg]
    (and
     (re-find #"lay-errorbar :y-min 0.5 is not a column" msg)
     (re-find #"one bound per row" msg)
     (re-find #"pj/lay-band-h" msg)))
   v147_l543)))


(def
 v150_l557
 (try
  (->
   measurements
   (pj/lay-point :treatment :mean)
   (pj/lay-errorbar {:y-min :ci-lo})
   pj/plan)
  (catch Exception e (ex-message e))))


(deftest
 t151_l563
 (is
  ((fn
    [msg]
    (and
     (re-find #"requires :y-min and :y-max columns" msg)
     (re-find #"got :y-min alone" msg)))
   v150_l557)))


(def v152_l567 (kind/doc #'pj/lay-rule-v))


(def
 v153_l569
 (->
  (rdatasets/datasets-iris)
  (pj/lay-point :sepal-length :sepal-width)
  (pj/lay-rule-v {:x-intercept 6.0})))


(deftest
 t154_l573
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 150 (:points s)) (pos? (:lines s)))))
   v153_l569)))


(def
 v156_l580
 (->
  {:date
   [#inst "2024-01-01T00:00:00.000-00:00"
    #inst "2024-04-01T00:00:00.000-00:00"
    #inst "2024-08-01T00:00:00.000-00:00"],
   :value [3 5 9]}
  (pj/lay-line :date :value)
  (pj/lay-rule-v
   {:x-intercept (java.time.LocalDate/parse "2024-06-01"),
    :color "#c0392b"})))


(deftest
 t157_l586
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 1 (:panels s)) (= 2 (:lines s)))))
   v156_l580)))


(def v158_l590 (kind/doc #'pj/lay-rule-h))


(def
 v159_l592
 (->
  (rdatasets/datasets-iris)
  (pj/lay-point :sepal-length :sepal-width)
  (pj/lay-rule-h {:y-intercept 3.0})))


(deftest
 t160_l596
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 150 (:points s)) (pos? (:lines s)))))
   v159_l592)))


(def v161_l600 (kind/doc #'pj/lay-band-v))


(def
 v162_l602
 (->
  (rdatasets/datasets-iris)
  (pj/lay-point :sepal-length :sepal-width)
  (pj/lay-band-v {:x-min 5.5, :x-max 6.5})))


(deftest
 t163_l606
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 150 (:points s))))
   v162_l602)))


(def v164_l609 (kind/doc #'pj/lay-band-h))


(def
 v165_l611
 (->
  (rdatasets/datasets-iris)
  (pj/lay-point :sepal-length :sepal-width)
  (pj/lay-band-h {:y-min 2.5, :y-max 3.5})))


(deftest
 t166_l615
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 150 (:points s))))
   v165_l611)))


(def v168_l620 (kind/doc #'pj/coord))


(def
 v170_l624
 (-> (rdatasets/datasets-iris) (pj/lay-bar :species) (pj/coord :flip)))


(deftest
 t171_l627
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 3 (:polygons s))))
   v170_l624)))


(def
 v173_l632
 (-> (rdatasets/datasets-iris) (pj/lay-bar :species) (pj/coord :polar)))


(deftest
 t174_l635
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (pos? (:polygons s))))
   v173_l632)))


(def v175_l638 (kind/doc #'pj/scale))


(def
 v177_l642
 (->
  (rdatasets/datasets-iris)
  (pj/lay-point :sepal-length :sepal-width)
  (pj/scale :x :log)))


(deftest
 t178_l645
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 150 (:points s))))
   v177_l642)))


(def
 v180_l650
 (->
  (rdatasets/datasets-iris)
  (pj/lay-point :sepal-length :sepal-width)
  (pj/scale :x {:domain [3 9]})))


(deftest
 t181_l653
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 150 (:points s))))
   v180_l650)))


(def
 v183_l659
 (->
  {:user [:a :b :c], :n [10 100 1000]}
  (pj/lay-point :user :n {:size :n, :x-type :categorical})
  (pj/scale :size :log)))


(deftest
 t184_l663
 (is ((fn [v] (= 3 (:points (pj/svg-summary v)))) v183_l659)))


(def
 v186_l669
 (->
  {:user [:a :b :c], :n [10 100 1000]}
  (pj/lay-point :user :n {:size :n, :x-type :categorical})
  (pj/scale :size {:range [3 16], :by :area, :from-zero true})))


(deftest
 t187_l673
 (is
  ((fn
    [v]
    (=
     16.0
     (->>
      v
      pj/plan
      :size-legend
      :entries
      (map :magnitude)
      (apply max))))
   v186_l669)))


(def
 v189_l680
 (->
  (rdatasets/datasets-iris)
  (pj/lay-point :sepal-length :sepal-width {:shape :species})
  (pj/scale
   :shape
   {:domain ["virginica" "versicolor" "setosa"],
    :values [:cross :plus :diamond]})))


(deftest
 t190_l685
 (is
  ((fn
    [v]
    (=
     [["virginica" :cross] ["versicolor" :plus] ["setosa" :diamond]]
     (mapv
      (juxt :label :shape)
      (:entries (:shape-legend (pj/plan v))))))
   v189_l680)))


(def v191_l691 (kind/doc #'pj/shape-symbols))


(def v192_l693 (pj/shape-symbols))


(deftest
 t193_l695
 (is ((fn [syms] (and (seq syms) (every? keyword? syms))) v192_l693)))


(def
 v195_l700
 (->
  (for [d (range 1 8)] {:day d, :v (mod d 3)})
  (pj/lay-point :day :v)
  (pj/scale
   :x
   {:type :linear,
    :breaks [1 2 3 4 5 6 7],
    :tick-labels ["Mon" "Tue" "Wed" "Thu" "Fri" "Sat" "Sun"]})))


(deftest
 t196_l706
 (is
  ((fn
    [v]
    (let
     [texts (set (:texts (pj/svg-summary v)))]
     (every? texts ["Mon" "Sun"])))
   v195_l700)))


(def v198_l712 (kind/doc #'pj/facet))


(def
 v199_l714
 (->
  (rdatasets/datasets-iris)
  (pj/lay-point :sepal-length :sepal-width {:color :species})
  (pj/facet :species)))


(deftest
 t200_l718
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 3 (:panels s)) (= 150 (:points s)))))
   v199_l714)))


(def v201_l722 (kind/doc #'pj/facet-grid))


(def
 v202_l724
 (->
  (rdatasets/reshape2-tips)
  (pj/lay-point :total-bill :tip {:color :sex})
  (pj/facet-grid :smoker :sex)))


(deftest
 t203_l728
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 4 (:panels s)) (= 244 (:points s)))))
   v202_l724)))


(def v205_l734 (kind/doc #'pj/arrange))


(def
 v206_l736
 (pj/arrange
  [(->
    (rdatasets/datasets-iris)
    (pj/lay-point :sepal-length :sepal-width {:color :species})
    (pj/options {:width 250, :height 200}))
   (->
    (rdatasets/datasets-iris)
    (pj/lay-point :petal-length :petal-width {:color :species})
    (pj/options {:width 250, :height 200}))]
  {:cols 2}))


(deftest t207_l744 (is ((fn [v] (pj/pose? v)) v206_l736)))


(def v208_l746 (kind/doc #'pj/overlay))


(def
 v210_l752
 (->
  {:cohort [:a :b :c], :growth [12 19 15], :tax [3 5 4]}
  pj/overlay
  (pj/lay-bar :growth :cohort {:color "#377eb8"})
  (pj/lay-bar :tax :cohort {:bar-width 0.4, :color "#e6550d"})))


(deftest
 t211_l757
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 1 (:panels s)) (= 6 (:polygons s)))))
   v210_l752)))


(def
 v213_l766
 (->
  {:cohort [:a :b :c],
   :growth [12 19 15],
   :tax [3 5 4],
   :spend [7 9 6]}
  (pj/lay-bar :growth :cohort {:color "#377eb8"})
  (pj/lay-bar :tax :cohort {:color "#e6550d", :overlay true})
  (pj/lay-bar :spend :cohort {:color "#4daf4a"})))


(deftest
 t214_l771
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and
      (= 2 (:panels s))
      (= 9 (:polygons s))
      (=
       #{"rgb(55,126,184)" "rgb(230,85,13)" "rgb(77,175,74)"}
       (disj (:colors s) "none")))))
   v213_l766)))


(def
 v216_l786
 [(->
   {:cohort [:a :b :c], :growth [12 19 15], :tax [3 5 4]}
   pj/overlay
   (pj/lay-bar :growth :cohort)
   (pj/lay-bar :tax :cohort)
   pj/svg-summary
   :panels)
  (->
   {:cohort [:a :b :c], :growth [12 19 15], :tax [3 5 4]}
   (pj/lay-bar :growth :cohort)
   (pj/lay-bar :tax :cohort)
   pj/overlay
   pj/svg-summary
   :panels)])


(deftest t217_l799 (is ((fn [v] (= [1 1] v)) v216_l786)))


(def v218_l801 (kind/doc #'pj/marginal))


(def
 v219_l803
 (->
  (rdatasets/datasets-iris)
  (pj/lay-point :sepal-length :sepal-width)
  (pj/marginal :top)))


(deftest
 t220_l807
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 2 (:panels s)) (= 150 (:points s)))))
   v219_l803)))


(def
 v222_l814
 (->
  (rdatasets/datasets-iris)
  (pj/lay-point :sepal-length :sepal-width)
  (pj/marginal :top :histogram {:size 0.3})))


(deftest
 t223_l818
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 2 (:panels s)) (= 150 (:points s)) (= 9 (:polygons s)))))
   v222_l814)))


(def
 v225_l826
 (->
  (rdatasets/datasets-iris)
  (pj/lay-point :sepal-length :sepal-width)
  (pj/marginal :right)))


(deftest
 t226_l830
 (is
  ((fn
    [v]
    (let
     [s
      (pj/svg-summary v)
      panels
      (mapv
       (fn* [p1__141516#] (-> p1__141516# :plan :panels first))
       (:sub-plots (pj/plan v)))]
     (and
      (= 2 (:panels s))
      (= 150 (:points s))
      (= (:y-domain (first panels)) (:y-domain (second panels))))))
   v225_l826)))


(def v228_l842 (kind/doc #'pj/plot))


(def v230_l847 (-> tiny (pj/lay-point :x :y)))


(deftest
 t231_l850
 (is
  ((fn [v] (let [s (pj/svg-summary v)] (= 5 (:points s)))) v230_l847)))


(def
 v233_l857
 (pj/plot {:height [150 160 170 175], :weight [50 60 72 78]}))


(deftest
 t234_l860
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (= 1 (:panels s)) (= 4 (:points s)))))
   v233_l857)))


(def v235_l864 (kind/doc #'pj/options))


(def
 v237_l868
 (->
  tiny
  (pj/lay-point :x :y)
  (pj/options {:width 400, :height 200, :title "Small Plot"})))


(deftest
 t238_l872
 (is
  ((fn
    [v]
    (let
     [s (pj/svg-summary v)]
     (and (< (:width s) 500) (some #{"Small Plot"} (:texts s)))))
   v237_l868)))


(def v240_l878 (kind/doc #'pj/pose?))


(def v242_l882 (pj/pose? (-> tiny (pj/pose :x :y) pj/lay-point)))


(deftest t243_l884 (is (true? v242_l882)))


(def v244_l886 (kind/doc #'pj/plan?))


(def v246_l890 (pj/plan? (pj/plan (pj/lay-point tiny :x :y))))


(deftest t247_l892 (is (true? v246_l890)))


(def v248_l894 (kind/doc #'pj/leaf-plan?))


(def v250_l899 (pj/leaf-plan? (pj/plan (pj/lay-point tiny :x :y))))


(deftest t251_l901 (is (true? v250_l899)))


(def v252_l903 (kind/doc #'pj/composite-plan?))


(def
 v254_l908
 (pj/composite-plan?
  (pj/plan
   (pj/arrange [(pj/lay-point tiny :x :y) (pj/lay-point tiny :x :y)]))))


(deftest t255_l912 (is (true? v254_l908)))


(def v256_l914 (kind/doc #'pj/draft?))


(def v258_l919 (pj/draft? (pj/draft (pj/lay-point tiny :x :y))))


(deftest t259_l921 (is (true? v258_l919)))


(def v260_l923 (kind/doc #'pj/leaf-draft?))


(def v262_l928 (pj/leaf-draft? (pj/draft (pj/lay-point tiny :x :y))))


(deftest t263_l930 (is (true? v262_l928)))


(def v264_l932 (kind/doc #'pj/composite-draft?))


(def
 v266_l937
 (pj/composite-draft?
  (pj/draft
   (pj/arrange [(pj/lay-point tiny :x :y) (pj/lay-point tiny :x :y)]))))


(deftest t267_l941 (is (true? v266_l937)))


(def v268_l943 (kind/doc #'pj/plan-layer?))


(def
 v270_l947
 (pj/plan-layer?
  (first
   (:layers (first (:panels (pj/plan (pj/lay-point tiny :x :y))))))))


(deftest t271_l949 (is (true? v270_l947)))


(def v272_l951 (kind/doc #'pj/layer-type?))


(def v274_l955 (pj/layer-type? (pj/layer-type-lookup :point)))


(deftest t275_l957 (is (true? v274_l955)))


(def v276_l959 (kind/doc #'pj/membrane?))


(def v278_l964 (pj/membrane? (pj/membrane (pj/lay-point tiny :x :y))))


(deftest t279_l966 (is (true? v278_l964)))


(def v281_l970 (kind/doc #'pj/draft))


(def
 v283_l977
 (->
  (rdatasets/datasets-iris)
  (pj/pose :sepal-length :sepal-width)
  pj/lay-point
  pj/draft
  kind/pprint))


(deftest
 t284_l983
 (is
  ((fn
    [d]
    (and
     (pj/leaf-draft? d)
     (= 1 (count (:layers d)))
     (= :point (:mark (first (:layers d))))))
   v283_l977)))


(def v285_l987 (kind/doc #'pj/plan))


(def v287_l991 (def plan1 (-> tiny (pj/lay-point :x :y) pj/plan)))


(def v288_l995 plan1)


(deftest
 t289_l997
 (is
  ((fn [m] (and (= 600 (:width m)) (= "x" (:x-label m)))) v288_l995)))


(def v290_l1000 (kind/doc #'pj/frames))


(def v292_l1006 (-> plan1 pj/frames kind/pprint))


(deftest
 t293_l1008
 (is
  ((fn
    [m]
    (and
     (= [0.0 0.0 600.0 400.0] (:canvas m))
     (= 1 (count (:panels m)))
     (true? (-> m :panels first :invertible?))
     (= 4 (count (-> m :panels first :frames :drawing-area)))))
   v292_l1006)))


(def
 v295_l1021
 (let
  [f
   (pj/frames
    (pj/arrange
     [(pj/lay-point tiny :x :y) (pj/lay-line tiny :x :y)]
     {:width 700, :height 300}))
   [_ _ cw ch]
   (:canvas f)
   boxes
   (mapv
    (fn* [p1__141517#] (-> p1__141517# :frames :panel-box))
    (:panels f))
   inside?
   (fn
    [[x y w h]]
    (and (>= x 0) (>= y 0) (<= (+ x w) cw) (<= (+ y h) ch)))]
  {:canvas (:canvas f),
   :panel-boxes boxes,
   :every-box-inside-the-canvas (every? inside? boxes),
   :panel-rectangle-keys
   (mapv
    (fn* [p1__141518#] (vec (keys (:frames p1__141518#))))
    (:panels f))}))


(deftest
 t296_l1033
 (is
  ((fn
    [m]
    (and
     (= [0.0 0.0 700.0 300.0] (:canvas m))
     (= 2 (count (:panel-boxes m)))
     (apply not= (map first (:panel-boxes m)))
     (true? (:every-box-inside-the-canvas m))
     (every?
      (fn* [p1__141519#] (= [:panel-box :drawing-area] p1__141519#))
      (:panel-rectangle-keys m))))
   v295_l1021)))


(def v298_l1047 (kind/doc #'pj/to-drawing))


(def v299_l1049 (pj/to-drawing (-> plan1 pj/frames :panels first) 2 5))


(deftest t300_l1051 (is ((fn [v] (= 2 (count v))) v299_l1049)))


(def v301_l1053 (kind/doc #'pj/to-data))


(def
 v303_l1057
 (pj/to-drawing
  (-> plan1 pj/frames :panels first)
  {:x [2 3], :y [5 6]}))


(deftest
 t304_l1060
 (is
  ((fn
    [ds]
    (and
     (= [:x :y] (vec (tc/column-names ds)))
     (= 2 (tc/row-count ds))))
   v303_l1057)))


(def
 v306_l1065
 (let
  [panel (-> plan1 pj/frames :panels first)]
  (->>
   (pj/to-drawing panel 2 5)
   (apply pj/to-data panel)
   (mapv (fn* [p1__141520#] (Math/round (double p1__141520#)))))))


(deftest t307_l1070 (is ((fn [v] (= [2 5] v)) v306_l1065)))


(def
 v309_l1077
 (let
  [panel
   (->
    {:species ["setosa" "versicolor" "virginica"],
     :count [12.0 19.0 8.0]}
    (pj/lay-bar :species :count)
    pj/frames
    :panels
    first)]
  {:in 1.5,
   :drawing-x (first (pj/to-drawing panel 1.5 10.0)),
   :back
   (first
    (pj/to-data panel (first (pj/to-drawing panel 1.5 10.0)) 10.0))}))


(deftest
 t310_l1086
 (is ((fn [m] (= "versicolor" (:back m))) v309_l1077)))


(def v311_l1088 (kind/doc #'pj/svg-summary))


(def
 v312_l1090
 (->
  (rdatasets/datasets-iris)
  (pj/lay-point :sepal-length :sepal-width {:color :species})
  pj/svg-summary))


(deftest
 t313_l1093
 (is ((fn [m] (and (= 1 (:panels m)) (= 150 (:points m)))) v312_l1090)))


(def v314_l1096 (kind/doc #'pj/valid-pose?))


(def v315_l1098 (pj/valid-pose? (pj/lay-point tiny :x :y)))


(deftest t316_l1100 (is (true? v315_l1098)))


(def v317_l1102 (kind/doc #'pj/explain-pose))


(def v318_l1104 (pj/explain-pose (pj/lay-point tiny :x :y)))


(deftest t319_l1106 (is (nil? v318_l1104)))


(def v320_l1108 (kind/doc #'pj/valid-plan?))


(def v321_l1110 (pj/valid-plan? plan1))


(deftest t322_l1112 (is (true? v321_l1110)))


(def v323_l1114 (kind/doc #'pj/explain-plan))


(def v324_l1116 (pj/explain-plan plan1))


(deftest t325_l1118 (is (nil? v324_l1116)))


(def v327_l1135 (kind/doc #'pj/membrane))


(def
 v329_l1145
 (let
  [m (pj/membrane (pj/lay-point tiny :x :y))]
  {:membrane? (pj/membrane? m),
   :width (membrane.ui/width m),
   :height (membrane.ui/height m),
   :record-keys (sort (filter keyword? (keys m)))}))


(deftest
 t330_l1151
 (is
  ((fn
    [info]
    (and
     (:membrane? info)
     (= 600 (:width info))
     (= 400 (:height info))
     (= [:drawables :height :width] (:record-keys info))))
   v329_l1145)))


(def v331_l1157 (kind/doc #'pj/->pose))


(def v333_l1164 (pj/pose? (pj/->pose tiny)))


(deftest t334_l1166 (is (true? v333_l1164)))


(def v335_l1168 (kind/doc #'pj/infer-mapping))


(def
 v337_l1174
 (->
  {:height [150 160 170], :weight [50 60 72]}
  pj/->pose
  pj/infer-mapping
  :mapping))


(deftest
 t338_l1179
 (is ((fn [m] (= {:x :height, :y :weight} m)) v337_l1174)))


(def
 v340_l1184
 (let
  [built (pj/lay-point tiny :x :y)]
  (= (:mapping built) (:mapping (pj/infer-mapping built)))))


(deftest t341_l1188 (is (true? v340_l1184)))


(def v342_l1190 (kind/doc #'pj/pose->draft))


(def
 v344_l1196
 (pj/leaf-draft? (pj/pose->draft (pj/lay-point tiny :x :y))))


(deftest t345_l1199 (is (true? v344_l1196)))


(def v346_l1201 (kind/doc #'pj/plan->membrane))


(def v347_l1203 (def m1 (pj/plan->membrane plan1)))


(def v348_l1205 (pj/membrane? m1))


(deftest t349_l1207 (is (true? v348_l1205)))


(def v350_l1209 (kind/doc #'pj/valid-membrane?))


(def v351_l1211 (pj/valid-membrane? m1))


(deftest t352_l1213 (is (true? v351_l1211)))


(def v353_l1215 (kind/doc #'pj/explain-membrane))


(def v354_l1217 (pj/explain-membrane m1))


(deftest t355_l1219 (is (nil? v354_l1217)))


(def v356_l1221 (kind/doc #'pj/membrane->plot))


(def v357_l1223 (first (pj/membrane->plot m1 :svg {})))


(deftest t358_l1225 (is ((fn [v] (= :svg v)) v357_l1223)))


(def v359_l1227 (kind/doc #'pj/plan->plot))


(def v360_l1229 (first (pj/plan->plot plan1 :svg {})))


(deftest t361_l1231 (is ((fn [v] (= :svg v)) v360_l1229)))


(def v363_l1238 (kind/doc #'pj/draft->plan))


(def v364_l1240 (def draft1 (pj/draft (pj/lay-point tiny :x :y))))


(def v365_l1242 (pj/plan? (pj/draft->plan draft1)))


(deftest t366_l1244 (is (true? v365_l1242)))


(def v367_l1246 (kind/doc #'pj/draft->membrane))


(def v368_l1248 (pj/membrane? (pj/draft->membrane draft1)))


(deftest t369_l1250 (is (true? v368_l1248)))


(def v370_l1252 (kind/doc #'pj/draft->plot))


(def v371_l1254 (first (pj/draft->plot draft1 :svg {})))


(deftest t372_l1256 (is ((fn [v] (= :svg v)) v371_l1254)))


(def v374_l1260 (kind/doc #'pj/config))


(def v375_l1262 (pj/config))


(deftest t376_l1264 (is ((fn [m] (map? m)) v375_l1262)))


(def v377_l1266 (kind/doc #'pj/set-config!))


(def v378_l1268 (kind/doc #'pj/with-config))


(def
 v379_l1270
 (pj/with-config {:color-values :pastel1} (:color-values (pj/config))))


(deftest t380_l1273 (is ((fn [p] (= :pastel1 p)) v379_l1270)))


(def v382_l1279 (kind/doc #'pj/config-key-docs))


(def v383_l1281 (count pj/config-key-docs))


(deftest t384_l1283 (is ((fn [n] (= 45 n)) v383_l1281)))


(def v385_l1285 (kind/doc #'pj/plot-option-docs))


(def v386_l1287 (count pj/plot-option-docs))


(deftest t387_l1289 (is ((fn [n] (= 15 n)) v386_l1287)))


(def v388_l1291 (kind/doc #'pj/layer-option-docs))


(def v389_l1293 (count pj/layer-option-docs))


(deftest t390_l1295 (is ((fn [n] (= 58 n)) v389_l1293)))


(def v392_l1299 (kind/doc #'pj/layer-type-lookup))


(def v393_l1301 (pj/layer-type-lookup :smooth))


(deftest
 t394_l1303
 (is
  ((fn [m] (and (= :line (:mark m)) (= :loess (:stat m)))) v393_l1301)))


(def v395_l1306 (kind/doc #'pj/registered-layer-types))


(def v396_l1308 (count (pj/registered-layer-types)))


(deftest t397_l1310 (is ((fn [n] (= 26 n)) v396_l1308)))


(def v398_l1312 (first (pj/registered-layer-types)))


(deftest
 t399_l1314
 (is
  ((fn [[k m]] (and (keyword? k) (some? (:mark m)) (some? (:stat m))))
   v398_l1312)))


(def v401_l1322 (kind/doc #'pj/stat-doc))


(def v402_l1324 (pj/stat-doc :linear-model))


(deftest t403_l1326 (is ((fn [s] (string? s)) v402_l1324)))


(def v404_l1328 (kind/doc #'pj/mark-doc))


(def v405_l1330 (pj/mark-doc :point))


(deftest t406_l1332 (is ((fn [s] (string? s)) v405_l1330)))


(def v407_l1334 (kind/doc #'pj/position-doc))


(def v408_l1336 (pj/position-doc :dodge))


(deftest t409_l1338 (is ((fn [s] (string? s)) v408_l1336)))


(def v410_l1340 (kind/doc #'pj/scale-doc))


(def v411_l1342 (pj/scale-doc :linear))


(deftest t412_l1344 (is ((fn [s] (string? s)) v411_l1342)))


(def v413_l1346 (kind/doc #'pj/coord-doc))


(def v414_l1348 (pj/coord-doc :cartesian))


(deftest t415_l1350 (is ((fn [s] (string? s)) v414_l1348)))


(def v416_l1352 (kind/doc #'pj/membrane-mark-doc))


(def v417_l1354 (pj/membrane-mark-doc :point))


(deftest t418_l1356 (is ((fn [s] (string? s)) v417_l1354)))


(def v420_l1360 (kind/doc #'pj/save))


(def
 v422_l1364
 (let
  [path (str (java.io.File/createTempFile "plotje-example" ".svg"))]
  (->
   (rdatasets/datasets-iris)
   (pj/lay-point :sepal-length :sepal-width {:color :species})
   (pj/save path {:title "Iris Export"}))
  (.contains (slurp path) "<svg")))


(deftest t423_l1370 (is (true? v422_l1364)))


(def
 v425_l1375
 (let
  [path (str (java.io.File/createTempFile "plotje-example" ".png"))]
  (->
   (rdatasets/datasets-iris)
   (pj/lay-point :sepal-length :sepal-width {:color :species})
   (pj/save path))
  (with-open
   [in (java.io.FileInputStream. path)]
   (let
    [bs (byte-array 8)]
    (.read in bs)
    (mapv (fn* [p1__141521#] (bit-and p1__141521# 255)) (vec bs))))))


(deftest
 t426_l1384
 (is ((fn [bs] (= [137 80 78 71 13 10 26 10] bs)) v425_l1375)))


(def
 v428_l1389
 (let
  [path (str (java.io.File/createTempFile "plotje-example" ".out"))]
  (->
   (rdatasets/datasets-iris)
   (pj/lay-point :sepal-length :sepal-width {:color :species})
   (pj/save path {:format :png}))
  (with-open
   [in (java.io.FileInputStream. path)]
   (let
    [bs (byte-array 4)]
    (.read in bs)
    (mapv (fn* [p1__141522#] (bit-and p1__141522# 255)) (vec bs))))))


(deftest t429_l1398 (is ((fn [bs] (= [137 80 78 71] bs)) v428_l1389)))
