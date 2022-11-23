<template>
  <v-row cols="12">
    <v-col>
      <v-card color="blue-grey lighten-5">
        <v-card-title>
          <h3>{{ house.aptName }}</h3>
          <v-card-subtitle> - {{ house.dong + " " + house.jibun }} </v-card-subtitle>
        </v-card-title>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
// import { mapActions } from "vuex";

// const houseStore = "houseStore";

export default {
  name: "MapHouseListItem",
  data() {
    return {};
  },
  props: {
    house: Object,
  },
  methods: {
    // ...mapActions(houseStore, ["detailHouse"]),
    // selectHouse() {
    //   // console.log("listRow : ", this.house);
    //   // this.$store.dispatch("getHouse", this.house);
    //   this.detailHouse(this.house);
    // },
    // colorChange(flag) {
    //   this.isColor = flag;
    // },
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
</style>
