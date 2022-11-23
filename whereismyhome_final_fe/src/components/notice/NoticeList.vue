<template>
  <v-main>
    <h1 class="mb-5">글 목록</h1>
    <v-layout class="mt-5 align-center">
      <v-btn
        class="mx-1"
        color="#dbd6d2"
        @click.prevent="showWrite"
        v-show="this.memberInfo != null && this.memberInfo.type === 'admin'"
        >글쓰기</v-btn
      >
      <v-spacer></v-spacer>
      <v-text-field
        id="searchBar"
        class="mx-4"
        v-model="search"
        append-icon="search"
        label="Search"
        single-line
      ></v-text-field>
    </v-layout>
    <v-data-table
      :headers="headers"
      :items="notices"
      :search="search"
      :items-per-page="noticePerPage"
      class="elevation-1"
      @click:row="viewNotice"
    ></v-data-table>
  </v-main>
</template>

<script>
import { listNotice } from "@/api/notice";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NoticeList",

  data() {
    return {
      headers: [
        { text: "글번호", align: "center", value: "noForList", width: "12.5%" },
        {
          text: "제목",
          align: "start",
          sortable: false,
          value: "title",
        },
        { text: "조회수", align: "center", value: "hit", width: "12.5%" },
      ],
      notices: [],
      page: 1,
      noticePerPage: 5,
      search: "",
    };
  },

  created() {
    listNotice(
      ({ data }) => {
        if (data.msg === "success") {
          this.notices = data.payload.list;
          this.notices.forEach((element, index) => {
            element.noForList = index + 1;
          });
        }
      },
      (error) => {
        console.log(error);
      }
    );
  },

  computed: {
    ...mapState(memberStore, ["memberInfo"]),
  },

  methods: {
    viewNotice(data) {
      console.log("clicked");
      console.log(data);
      this.$router.push({ name: "noticeview", params: { articleNo: data.articleNo } });
    },

    showWrite() {
      this.$router.push({ name: "noticewrite" });
    },
  },
};
</script>

<style scope>
#searchBar {
  width: 30px;
}
</style>
