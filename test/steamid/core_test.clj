(ns steamid.core-test
  (:require [clojure.test :refer :all]
            [steamid.core :refer :all]))

(def test-steamid   "STEAM_0:1:50279161")
(def test-steamid64 "76561198060824051")
(def test-steamid3  "[U:1:100558323]")

(deftest is-steamidx
  (testing "regular steamids"
    (is (= test-steamid (steamid? test-steamid)))
    (is (= nil (steamid? "kevin_bacon"))))
    (is (= nil (steamid? test-steamid64)))
    (is (= nil (steamid? test-steamid3)))
  (testing "steamid64s"
    (is (= test-steamid64 (steamid64? test-steamid64)))
    (is (= nil (steamid64? "kevin_bacon"))))
    (is (= nil (steamid64? test-steamid3)))
    (is (= nil (steamid64? test-steamid)))
  (testing "steamid3s"
    (is (= test-steamid3 (steamid3? test-steamid3)))
    (is (= nil (steamid3? "kevin_bacon")))
    (is (= nil (steamid3? test-steamid64)))
    (is (= nil (steamid3? test-steamid)))))

(deftest x->steamid64
  (testing "steamid64 fn works"
    (is (= test-steamid64 (steamid64 test-steamid)))
    (is (= test-steamid64 (steamid64 test-steamid3)))))

(deftest x->steamid3
  (testing "steamid3 fn works"
    (is (= test-steamid3 (steamid3 test-steamid)))
    (is (= test-steamid3 (steamid3 test-steamid64)))))

(deftest x->steamid
  (testing "steamid fn works"
    (is (= test-steamid (steamid test-steamid3)))
    (is (= test-steamid (steamid test-steamid64)))))
