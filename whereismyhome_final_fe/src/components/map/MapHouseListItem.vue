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
          <v-btn color="orange lighten-1" text @click="expandPanel">
            거래내역
          </v-btn>
        </v-card-actions>
        <v-expand-transition>
          <div v-show="show">
            <v-divider></v-divider>
            <v-card-text>
              I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.
            </v-card-text>
          </div>
        </v-expand-transition>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
// import { mapActions } from "vuex";
import { dealList } from "@/api/house.js";

// const houseStore = "houseStore";

export default {
  name: "MapHouseListItem",
  data() {
    return {
      show: false,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    expandPanel() {
      this.show = !this.show;
      if(this.show){
        this.loadDealList();
      }
    },

    loadDealList() {
      const params = {aptCode : this.house.aptCode};
      dealList(
        params,
        ({data}) => {
          console.log(data);
        },
        (error) => {
          console.log(error);
        }
      );
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
</style>
