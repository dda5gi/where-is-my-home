<template>
  <v-container>
    <v-row>
      <v-col>
        <v-overflow-btn filled v-model="sidoCode" :items="sidos" @change="gugunList"></v-overflow-btn>
      </v-col>
      <v-col>
        <v-overflow-btn filled v-model="gugunCode" :items="guguns" @change="dongList"></v-overflow-btn>
      </v-col>
      <v-col>
        <v-overflow-btn filled v-model="dongCode" :items="dongs" @change="searchHouse"></v-overflow-btn>
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
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getDong", "getHouseList"]),
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

<style></style>
