<template>
  <v-main>
    <map-house-search></map-house-search>
    <v-container>
      <v-row>
        <v-col cols="4">
          <h2>아파트 목록</h2>
          <v-divider></v-divider>
          <map-house-list></map-house-list>
        </v-col>
        <v-col cols="8">
          <div id="map"></div>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
import { mapState } from "vuex";
import MapHouseSearch from "@/components/map/MapHouseSearch.vue";
import MapHouseList from "@/components/map/MapHouseList.vue";

const houseStore = "houseStore";
export default {
  name: "AppMap",
  components: {
    MapHouseSearch,
    MapHouseList,
  },
  data() {
    return {
      map: null,
      markerDatas: [],
      markers: [],
      centerPos: null,
    };
  },

  computed: {
    ...mapState(houseStore, ["houses"]),
  },

  watch: {
    houses(newHouses) {
      if (newHouses.length > 0) {
        this.removeMarkers();
        this.loadMarkers(newHouses);
        this.drawMarkers();
      }
    },
  },

  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.535887, 126.974063, 16),
        level: 6,
      };
      this.map = new kakao.maps.Map(container, options);
    },

    removeMarkers() {
      // 마커 지우기
      if (this.markers.length > 0) {
        this.markers.forEach((m) => {
          m.setMap(null);
        });
      }
    },

    loadMarkers(houses) {
      // 아파트 정보 불러온 마커에서 쓸 수 있게 변환
      console.log(houses);
      this.markerDatas = [];
      houses.forEach((element) => {
        let lat = parseFloat(element.lat);
        let lng = parseFloat(element.lng);
        this.markerDatas.push({
          title: element.aptName,
          latlng: new kakao.maps.LatLng(lat, lng),
        });
      });
    },

    // 마커 그리기
    drawMarkers() {
      this.markerDatas.forEach((md) => {
        const m = new kakao.maps.Marker({
          map: this.map,
          position: md.latlng,
          title: md.title,
        });
        this.markers.push(m);
      });

      const moveLocation = this.calcCenterLatlng();
      this.map.setCenter(moveLocation);
    },

    // 동 좌표 구하기
    calcCenterLatlng() {
      let lat = 0;
      let lng = 0;
      this.houses.forEach((e) => {
        lat += parseFloat(e.lat);
        lng += parseFloat(e.lng);
      });
      lat = lat / this.houses.length;
      lng = lng / this.houses.length;
      return new kakao.maps.LatLng(lat, lng);
    },
  },

  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAOMAP_KEY}`;
      /* global kakao */
      script.addEventListener("load", () => {
        kakao.maps.load(this.initMap);
      });
      document.head.appendChild(script);
    } else {
      this.initMap();
    }
  },
};
</script>

<style>
#map {
  width: 100%;
  height: 700px;
}
</style>
