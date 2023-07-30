<template>
  <div>
    <h2>게시글 작성</h2>
    <form @submit.prevent="createBoard">
      <div class="form-group">
        <label for="writer"><strong>작성자</strong></label>
        <input type="text" class="form-control" id="writer" v-model="board.writer" />
      </div>
      <div class="form-group">
        <label for="password"><strong>비밀번호</strong></label>
        <input type="password" class="form-control" id="password" v-model="board.password" />
      </div>
      <div class="form-group">
        <label for="title"><strong>제목</strong></label>
        <input type="text" class="form-control" id="title" v-model="board.title" />
      </div>
      <div class="form-group">
        <label for="content"><strong>내용</strong></label>
        <textarea class="form-control" id="content" v-model="board.content"></textarea>
      </div>
      <b-row rows="1"></b-row>
      <b-row>
        <b-col v-for="i in 5" :key="i" cols="2">
          <label :for="'tag' + i"
            ><strong>태그 {{ i }}</strong></label
          >
          <b-form-input :id="'tag' + i" v-model="tags[i - 1]"></b-form-input>
        </b-col>
      </b-row>
      <div class="d-flex justify-content-end">
        <button type="submit" class="btn btn-primary">글 작성</button>
      </div>
    </form>
  </div>
</template>

<script>
import http from "@/util/http";
export default {
  name: "BoardWrite",
  data() {
    return {
      tags: [],
      board: {
        writer: "",
        password: "",
        title: "",
        content: "",
        tags: [],
      },
    };
  },
  methods: {
    createBoard() {
      if (!this.board.writer || !this.board.password || !this.board.title || !this.board.content) {
        console.log(this.board);
        alert("모든 필수 입력란을 채워주세요.");
        return;
      }
      this.board.tags = this.tags.filter((tag) => tag.trim() !== "");
      http
        .post("/boards", this.board)
        .then(() => {
          alert("글 작성 완료");
          this.$router.push("/");
        })
        .catch((error) => {
          console.error("POST 요청 에러 : ", error);
        });
    },
  },
};
</script>

<style></style>
