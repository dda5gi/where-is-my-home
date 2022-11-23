<template>
  <v-main>
    <h1 class="mb-5">글 목록</h1>
    <v-layout row class="align-baseline">
      <v-flex xs4 class="mx-2">
        <v-text-field
          id="searchBar"
          color="#6667AB"
          class="mx-4"
          v-model="search"
          append-icon="search"
          label="Search"
          single-line
        ></v-text-field>
      </v-flex>
      <v-spacer></v-spacer>
      <v-flex xs3 class="text-right mx-2">페이지에 표시할 글 개수 :</v-flex>
      <v-flex xs1 class="me-5">
        <v-select
          v-model="itemSelected"
          :items="itemsPerPageOptions"
          item-text="name"
          item-value="value"
          outlined
          dense
          auto
          background-color="white"
        >
        </v-select>
      </v-flex>
    </v-layout>
    <v-data-table
      :headers="headers"
      :items="this.list"
      :search="search"
      :items-per-page="this.itemsPerPage"
      :page.sync="selectedPageNo"
      class="elevation-1"
      hide-default-footer
      @page-count="pageCount = $event"
      @click:row="viewNotice"
    ></v-data-table>
    <div class="text-center pt-2">
      <v-pagination v-model="selectedPageNo" :length="pageCount"></v-pagination>
    </div>
    <v-btn class="float-right mx-2 mt-1" color="#dbd6d2" @click.prevent="">글쓰기</v-btn>
  </v-main>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const noticeStore = "noticeStore";

export default {
  name: "NoticeList",

  data() {
    return {
      headers: [
        { text: "글번호", align: "center", value: "no", width: "12.5%" },
        {
          text: "제목",
          align: "start",
          sortable: false,
          value: "title",
        },
        { text: "조회수", align: "center", value: "hit", width: "12.5%" },
      ],
      itemsPerPageOptions: [
        { name: "5", value: "5" },
        { name: "10", value: "10" },
        { name: "20", value: "20" },
        { name: "all", value: "-1" },
      ],
      pageCount: 0,
      selectedPageNo: 1,
      search: "",
      itemSelected: "5",
    };
  },

  created() {
    const parse = parseInt(this.$route.params.pageNo);
    this.selectedPageNo = parse;
    this.onSelectItemsPerPage();
    this.onSelectPage();
    this.getList();
  },

  computed: {
    ...mapState(noticeStore, ["itemsPerPage", "page", "list"]),
  },

  watch: {
    itemSelected: "onSelectItemsPerPage",
    selectedPageNo: "onSelectPage",
  },

  methods: {
    ...mapMutations(noticeStore, [
      "SET_ITEMS_PER_PAGE",
      "SET_PAGE",
      "SET_NOTICE_NO",
      "SET_IS_MODIFY",
    ]),
    ...mapActions(noticeStore, ["getList"]),

    onSelectItemsPerPage() {
      const parse = parseInt(this.itemSelected);
      this.SET_ITEMS_PER_PAGE(parse);
    },

    onSelectPage() {
      this.SET_PAGE(this.selectedPageNo);
    },

    viewNotice(element) {
      this.SET_NOTICE_NO(element.articleNo);
      this.SET_IS_MODIFY(false);
      this.$router.push({ name: "noticeview" });
    },
  },
};
</script>

<style scoped></style>
