package main

import (
	"math/rand"
	"net/http"
	"time"

	"github.com/prometheus/client_golang/prometheus"
	"github.com/prometheus/client_golang/prometheus/promhttp"
)

var (
	randomGauge = prometheus.NewGauge(
		prometheus.GaugeOpts{
			Name: "tbz_random_metric",
			Help: "Random value between 0 and 100",
		})
)

func init() {
	prometheus.MustRegister(randomGauge)
	go func() {
		for {
			randomGauge.Set(float64(rand.Intn(101))) // 0–100
			time.Sleep(10 * time.Second)
		}
	}()
}

func main() {
	http.Handle("/metrics", promhttp.Handler())
	http.ListenAndServe(":2112", nil)
}
