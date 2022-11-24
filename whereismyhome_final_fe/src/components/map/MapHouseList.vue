<template>
  <v-container style="height: 650px; overflow-y: scroll" v-if="houses && houses.length != 0">
    <map-house-list-item
      @highLightMarker="highLightMarker"
      v-for="house in houses"
      :key="house.aptCode"
      :house="house"
    ></map-house-list-item>
  </v-container>
</template>

<script>
import MapHouseListItem from "@/components/map/MapHouseListItem.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "MapHouseList",
  components: {
    MapHouseListItem,
  },
  data() {
    return {};
  },
  created() {
    this.highLightMarker();
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
  methods: {
    // 자식 컴포넌트에서 받아온 아파트 이름을 부모에게 전달하는 코드
    highLightMarker(aptName) {
      this.$emit("highLightMarker", aptName);
    },
  },
};
</script>

<style></style>
