<template>
  <div>
    <h2>댓글 작성</h2>
    <form @submit.prevent="createComment">
      <div class="form-group">
        <label for="writer">작성자</label>
        <input type="text" class="form-control" id="writer" v-model="comment.writer" required />
      </div>
      <div class="form-group">
        <label for="password">비밀번호</label>
        <input
          type="password"
          class="form-control"
          id="password"
          v-model="comment.password"
          required
        />
      </div>
      <div class="form-group">
        <label for="content">댓글 내용</label>
        <textarea class="form-control" id="content" v-model="comment.text" required></textarea>
      </div>
      <button type="submit" class="btn btn-primary">댓글 작성</button>
    </form>
  </div>
</template>

<script>
import http from "@/util/http";

export default {
  name: "CommentWrite",
  props: {
    boardId: Number,
  },
  data() {
    return {
      comment: {
        boardId: this.boardId,
        writer: "",
        password: "",
        text: "",
      },
    };
  },
  methods: {
    createComment() {
      if (!this.comment.writer || !this.comment.password || !this.comment.text) {
        alert("모든 입력란을 채워주세요.");
        return;
      }
      console.log(this.comment);
      http
        .post("/comments", this.comment)
        .then(() => {
          alert("댓글 작성 완료");
          this.comment = {
            boardId: this.comment.boardId,
            writer: "",
            password: "",
            text: "",
          };
          this.$emit("getCommentList");
        })
        .catch((error) => {
          console.error("POST 요청 에러 : ", error);
        });
    },
  },
};
</script>

<style></style>
