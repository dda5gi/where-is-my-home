<template>
  <v-container style="height: 650px; overflow-y: scroll" v-if="houses && houses.length != 0">
    <map-house-list-item v-for="house in houses" :key="house.aptCode" :house="house"></map-house-list-item>
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
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
};
</script>

<style></style>
