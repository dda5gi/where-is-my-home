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
        <v-btn>즐겨찾기</v-btn>
      </v-col>
      <v-divider vertical></v-divider>
      <v-divider vertical></v-divider>
      <v-divider vertical></v-divider>
      <v-divider vertical></v-divider>
      <v-divider vertical></v-divider>
      <v-divider vertical></v-divider>
      <v-divider vertical></v-divider>
      <v-divider vertical></v-divider>
      <v-col cols="1">
        <h3>즐겨찾기 :</h3>
      </v-col>
      <v-col cols="3">
        <v-container style="height: 100px; overflow-y: scroll">
          <v-list>
            <v-list-item>
              <v-list-item-content>서울 중구 중구가시키드나</v-list-item-content>
              <v-list-item-action>
                <v-btn>
                  <v-icon color="grey lighten-1">delete</v-icon>
                </v-btn>
              </v-list-item-action>
            </v-list-item>
            <v-list-item>더미</v-list-item>
            <v-list-item>더미</v-list-item>
          </v-list>
        </v-container>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
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
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
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
  },
};
</script>

<style>
div.col {
  padding: 12px, 12px, 0px, 12px;
}
</style>
