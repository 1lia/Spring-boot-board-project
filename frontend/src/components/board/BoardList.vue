<template>
  <div class="board-list">
    <h2>게시판 목록</h2>
    <b-table :items="boards" :fields="fields" :hover="true" @row-clicked="moveDetail"> </b-table>

    <div class="d-flex justify-content-end">
      <b-button id="write-btn" @click="moveWrite">글쓰기</b-button>
    </div>
  </div>
</template>

<script>
import http from "@/util/http";
export default {
  name: "BoardList",
  data() {
    return {
      page: 0,
      boards: [],
      fields: [
        { key: "title", label: "제목" },
        { key: "name", label: "작성자" },
        { key: "commentCount", label: "댓글 수" },
        { key: "likeCount", label: "좋아요 수" },
        { key: "hit", label: "조회 수" },
        { key: "createTime", label: "작성 시간" },
      ],
    };
  },

  created() {
    this.getBoardList();
  },

  methods: {
    getBoardList() {
      http
        .get("/boards", {
          params: {
            type: "해시태그",
            word: "",
            page: this.page,
          },
        })
        .then(({ data }) => {
          this.boards = data;
        })
        .catch((error) => {
          console.error("GET 요청 에러 : ", error);
        });
    },

    moveDetail(item) {
      this.$router.push({ name: "boardDetail", params: { boardId: item.boardId } });
    },

    moveWrite() {
      this.$router.push({ name: "boardWrite" });
    },
  },
};
</script>

<style></style>
