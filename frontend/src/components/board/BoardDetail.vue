<template>
  <div>
    <h2>{{ this.board.title }}</h2>
    <p><strong>작성자:</strong> {{ this.board.writer }}</p>
    <p><strong>제목:</strong>{{ this.board.title }}</p>
    <p><strong>내용:</strong>{{ this.board.content }}</p>
    <p><strong>댓글 수:</strong> {{ this.board.commentCount }}</p>
    <p><strong>좋아요 수:</strong> {{ this.board.likeCount }}</p>
    <p><strong>조회 수:</strong> {{ this.board.hit }}</p>
    <p><strong>작성 시간:</strong> {{ this.board.createTime }}</p>
  </div>
</template>

<script>
import http from "@/util/http";
export default {
  name: "BoardDetail",
  data() {
    return {
      board: {},
    };
  },

  created() {
    this.getBoardDetail(this.$route.params.boardId);
  },

  methods: {
    getBoardDetail(boardId) {
      http
        .get("/boards/detail", {
          params: {
            boardId: boardId,
          },
        })
        .then(({ data }) => {
          this.board = data;
        })
        .catch((error) => {
          console.error("GET 요청 에러 : ", error);
        });
    },
  },
};
</script>

<style></style>
