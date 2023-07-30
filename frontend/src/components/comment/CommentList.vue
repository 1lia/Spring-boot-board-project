<template>
  <div class="comment-list">
    <h3>댓글 목록</h3>
    <div>
      <label for="password"><strong>비밀번호</strong></label>
      <input type="password" class="form-control" id="password" v-model="password" />
    </div>
    <ul>
      <li v-for="comment in comments" :key="comment.id">
        <span><strong>작성자</strong> : {{ comment.writer }}</span>
        <span><strong>내용</strong> : {{ comment.text }}</span>
        <span><strong>작성시간</strong> : {{ comment.createTime }}</span>
        <button @click="checkpassword(comment.commentId)">삭제</button>
      </li>
    </ul>
  </div>
</template>

<script>
import http from "@/util/http";
export default {
  name: "CommentList",
  props: {
    comments: [],
  },
  data() {
    return {
      password: null,
    };
  },

  methods: {
    checkpassword(commentId) {
      if (confirm("댓글을 삭제하시겠습니까?")) {
        const password = prompt("비밀번호를 입력하세요:");
        http
          .get("/comments/check", {
            params: {
              commentId: commentId,
              password: password,
            },
          })
          .then(({ data }) => {
            if (data == 1) {
              this.deletecomment(commentId);
            } else {
              alert("비밀번호가 틀렸습니다");
            }
          })
          .catch((error) => {
            console.error("GET 요청 에러 : ", error);
          });
      }
      console.log(commentId);
    },

    deletecomment(commentId) {
      http
        .delete(`/comments/${commentId}`)
        .then(() => {
          alert("댓글이 삭제되었습니다.");
          this.$router.push("/");
        })
        .catch((error) => {
          console.error("DELETE 요청 에러 : ", error);
        });
    },
  },
};
</script>

<style></style>
