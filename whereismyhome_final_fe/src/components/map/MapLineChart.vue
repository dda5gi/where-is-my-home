<template>
  <LineChartGenerator :height="250" :chart-options="chartOptions" :chart-data="chartData"> </LineChartGenerator>
</template>

<script>
import { Chart, registerables } from "chart.js";
Chart.register(...registerables);
import { Line as LineChartGenerator } from "vue-chartjs/legacy";

export default {
  name: "MapLineChart",
  components: {
    LineChartGenerator,
  },
  props: ["chartData", "chartOptions"],
};
</script>

<style></style>
