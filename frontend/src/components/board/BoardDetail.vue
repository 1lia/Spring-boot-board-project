<template>
  <div>
    <div>
      <h2>게시글 상세</h2>
      <button class="btn btn-primary" @click="updateLike(1)">좋아요</button>
      <button class="btn btn-danger" @click="updateLike(-1)">싫어요</button>
    </div>

    <div>
      조회수 : {{ this.board.hit }} 좋아요 수 : {{ this.board.likeCount }} 작성시간 :
      {{ this.board.createTime }}
    </div>
    <form @submit.prevent="modifyBoard">
      <div class="form-group">
        <label for="writer"><strong>작성자</strong></label>
        <input
          type="text"
          class="form-control"
          id="writer"
          v-model="board.writer"
          :disabled="!check"
          required
        />
      </div>
      <div class="form-group">
        <label for="password"><strong>비밀번호</strong></label>
        <input type="password" class="form-control" id="password" v-model="password" required />
      </div>
      <div class="form-group">
        <label for="title"><strong>제목</strong></label>
        <input
          type="text"
          class="form-control"
          id="title"
          v-model="board.title"
          :disabled="!check"
          required
        />
      </div>
      <div class="form-group">
        <label for="content"><strong>내용</strong></label>
        <textarea
          class="form-control"
          id="content"
          v-model="board.content"
          :disabled="!check"
          required
        ></textarea>
      </div>
      <b-row rows="1"></b-row>
      <b-row>
        <b-col v-for="i in 5" :key="i" cols="2">
          <label :for="'tag' + i"
            ><strong>태그 {{ i }}</strong></label
          >
          <b-form-input :id="'tag' + i" v-model="tags[i - 1]" :disabled="!check"></b-form-input>
        </b-col>
      </b-row>
      <button v-if="check" type="submit" class="btn btn-primary">글 수정</button>
    </form>
    <button v-if="check" class="btn btn-danger" @click="deleteBoard">글 삭제</button>
    <button v-if="!check" class="btn btn-primary" @click="checkPassword">비밀번호 확인</button>
    <comment-write :boardId="boardId" @getCommentList="getCommentList(boardId)"></comment-write>
    <comment-list :comments="comments" @getCommentList="getCommentList(boardId)"></comment-list>
    <b-button v-if="hasmorecomments" @click="getCommentListAdd(boardId)">더보기</b-button>
  </div>
</template>

<script>
import http from "@/util/http";
import CommentWrite from "../comment/CommentWrite.vue";
import CommentList from "@/components/comment/CommentList.vue";
export default {
  components: { CommentWrite, CommentList },
  name: "BoardDetail",

  data() {
    return {
      page: 0,
      tags: [],
      boardId: null,
      check: false,
      password: null,
      board: {},
      comments: [],
      hasmorecomments: true,
    };
  },

  created() {
    this.boardId = this.$route.params.boardId;
    this.getBoardDetail(this.boardId);
    this.getCommentList(this.boardId, 0);
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
          this.tags = this.board.tags;
        })
        .catch((error) => {
          console.error("GET 요청 에러 : ", error);
        });
    },

    modifyBoard() {
      this.board.tags = this.tags.filter((tag) => tag.trim() !== "");
      http
        .put(`/boards/${this.boardId}`, {
          writer: this.board.writer,
          title: this.board.title,
          content: this.board.content,
          tags: this.board.tags,
        })
        .then(() => {
          alert("글이 수정되었습니다.");
          this.$router.push(`/`);
        })
        .catch((error) => {
          console.error("PUT 요청 에러 : ", error);
        });
    },

    deleteBoard() {
      http
        .delete(`/boards/${this.boardId}`)
        .then(() => {
          alert("글이 삭제되었습니다.");
          this.$router.push("/");
        })
        .catch((error) => {
          console.error("DELETE 요청 에러 : ", error);
        });
    },

    checkPassword() {
      if (!this.password) {
        alert("비밀번호를 입력해주세요");
        return;
      }
      http
        .get("/boards/check", {
          params: {
            boardId: this.boardId,
            password: this.password,
          },
        })
        .then(({ data }) => {
          if (data == 1) {
            this.check = true;
            alert("성공");
          } else {
            alert("비밀번호가 틀렸습니다");
          }
        })
        .catch((error) => {
          console.error("GET 요청 에러 : ", error);
        });
    },

    getCommentList(boardId) {
      this.boardId = this.$route.params.boardId;
      this.page = 0;
      http
        .get("/comments", {
          params: {
            boardId: boardId,
            page: 0,
          },
        })
        .then(({ data }) => {
          this.comments = data;
          if (this.comments.length < 5) {
            this.hasmorecomments = false;
          }
        })
        .catch((error) => {
          console.error("GET 요청 에러 : ", error);
        });
    },
    getCommentListAdd(boardId) {
      this.boardId = this.$route.params.boardId;
      this.page = this.page + 1;
      http
        .get("/comments", {
          params: {
            boardId: boardId,
            page: this.page,
          },
        })
        .then(({ data }) => {
          this.comments = this.comments.concat(data);
          if (this.comments.length < 5 * (this.page + 1)) {
            this.hasmorecomments = false;
          }
        })
        .catch((error) => {
          console.error("GET 요청 에러 : ", error);
        });
    },
    updateLike(like) {
      console.log(this.boardId);
      http
        .put("/boards/like", {
          boardId: this.boardId,
          like: like,
        })
        .then(() => {
          this.board.likeCount += like;
          alert("반영되었습니다");
        })
        .catch((error) => {
          console.error("PUT 요청 에러 : ", error);
        });
    },
  },
};
</script>

<style></style>
