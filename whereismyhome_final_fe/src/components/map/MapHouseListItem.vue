<template>
  <v-row cols="12">
    <v-col>
      <v-card color="blue-grey lighten-5">
        <v-card-title>
          <v-icon>apartment</v-icon>
          <h3>{{ house.aptName }}</h3>
          <v-card-subtitle> - {{ house.dong + " " + house.jibun }} </v-card-subtitle>
        </v-card-title>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text>위치보기</v-btn>
          <v-btn color="orange darken-1" text @click="expandPanel">거래내역</v-btn>
        </v-card-actions>
        <v-expand-transition>
          <div v-show="show">
            <v-divider></v-divider>
            <v-card-text v-if="show">
              <div v-if="data != null">
                <v-chip
                  color="rgb(38, 150, 190, 0.5)"
                  @click="drawChart(el)"
                  class="ma-2"
                  label
                  v-for="(el, idx) in data.areas"
                  :key="idx"
                >
                  {{ el }}m<sup>2</sup>
                </v-chip>
              </div>
              <map-line-chart :chartData="chartData"></map-line-chart>
            </v-card-text>
          </div>
        </v-expand-transition>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import { dealList } from "@/api/house.js";
import MapLineChart from "./MapLineChart.vue";

export default {
  name: "MapHouseListItem",
  components: {
    MapLineChart,
  },
  data() {
    return {
      chartData: {
        labels: [2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023],
        datasets: [
          {
            label: "거래액 동향",
            backgroundColor: "rgb(37, 150, 190, 0.5)",
            pointBackgroundColor: "rgb(17, 61, 76)",
            fill: true,
            tension: 0.5,
            borderColor: "rgb(17, 61, 76)",
            pointBorderColor: "rgb(17, 61, 76)",
            borderWidth: 2,
            pointBorderWidth: 3,
            data: [300, 300, 300, 300, 300, 300, 300, 300, 300, 300],
          },
        ],
      },

      show: false,
      data: null,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    expandPanel() {
      this.show = !this.show;
      if (this.show) {
        this.loadAreaDealList();
      }
    },

    loadAreaDealList() {
      const params = { aptCode: this.house.aptCode };
      dealList(
        params,
        ({ data }) => {
          this.data = data.payload;
          console.log(this.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    drawChart(area) {
      this.chartData.datasets[0].data = [];
      this.chartData.labels = [];
      this.data.list[area].forEach((e) => {
        this.chartData.datasets[0].data.push(parseInt(e.dealAmount.split(",").join("")));
        this.chartData.labels.push(e.date);
      });
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
div.v-card__title {
  padding: 15px 16px 0px 16px;
}
</style>
