<template>
  <v-container>
    <v-row align="center">
      <v-col cols="1">
        <h3>지역 선택 :</h3>
      </v-col>
      <v-col cols="2">
        <v-overflow-btn v-model="sidoCode" :items="sidos" @change="gugunList"></v-overflow-btn>
      </v-col>
      <v-col cols="2">
        <v-overflow-btn v-model="gugunCode" :items="guguns" @change="dongList"></v-overflow-btn>
      </v-col>
      <v-col cols="2">
        <v-overflow-btn v-model="dongCode" :items="dongs" @change="searchHouse"></v-overflow-btn>
      </v-col>
      <v-col cols="1">
        <v-btn @click="registerRegion">즐겨찾기 등록</v-btn>
      </v-col>
      <v-divider vertical></v-divider>
      <v-divider vertical></v-divider>
      <v-divider vertical></v-divider>
      <v-col cols="1">
        <h3>즐겨찾기 :</h3>
      </v-col>
      <v-col cols="3">
        <v-container style="height: 100px; overflow-y: scroll">
          <v-list>
            <v-list-item v-for="(favor) in favors" :key="favor.dongCode">
              <v-btn @click="searchHouseWithFavor(favor.dongCode)">{{favor.regionName}}</v-btn>
              <v-list-item-action>
                <v-btn @click="deleteRegion(favor.dongCode)">
                  <v-icon color="grey lighten-1">delete</v-icon>
                </v-btn>
              </v-list-item-action>
            </v-list-item>
          </v-list>
        </v-container>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import {registerFavor, deleteFavor} from "@/api/house.js";
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "MapHouseSearch",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses", "favors"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.CLEAR_HOUSE_LIST();
    this.getSido();
    this.getFavorList();
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getDong", "getHouseList", "getFavorList"]),
    ...mapMutations(houseStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_DONG_LIST", "CLEAR_HOUSE_LIST"]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchHouse() {
      if (this.dongCode) this.getHouseList(this.dongCode);
    },
    searchHouseWithFavor(dongCode) {
      this.getHouseList(dongCode);
    },
    registerRegion(){
      if(this.dongCode == null) {
        alert("지역을 먼저 선택해주세요")
      } else {
        let exist = false;
        this.favors.forEach((e) => {
          if(e.dongCode == this.dongCode) {
            alert("이미 등록된 지역입니다.")
            exist = true;
          }
        })
        if(!exist) {
          const params = {dongCode : this.dongCode};
          registerFavor(
            params,
            ({data}) => {
              console.log(data);
              this.getFavorList();
            },
          )
        }
      }
    },
    deleteRegion(dongCode) {
      const params = {dongCode : dongCode};
      deleteFavor(
        params,
        ({data}) =>{
          console.log(data);
          this.getFavorList();
        }
      )
    }
  },
};
</script>

<style>
div.col {
  padding: 12px, 12px, 0px, 12px;
}
</style>
