## Git 이란?
* 버전 관리 시스템 (version control system)
* 프로젝트 소스 코드 버전 관리 목적
* git bash : unix, linux 명령어 활용할 수 있도록 기능 제공

## git bash
### 기본 명령어
#### git config
* git 환경변수 설정(유저명, 이메일)
* git config --global user.name chbong
* git config --global user.email chbong@naver.com

#### git init
* 현재 directory에서 버전 관리 (git) 작업을 시작하겠다.
* .git 파일 내 버전 정보 백업

#### git status
* 파일 버전 관리 추적
* 최신 objects, index 파일 비교 -> 차이점 출력

#### git pull
* git pull origin [소스코드 가져올 branch 명]

#### git add
* git 파일 추적 시작
* git add [commit 할 파일명]
* git add * or git add.
 - directory 내 모든 파일 add

#### git restore --staged .
* add한 파일 unstage

#### git commit
* git commit -(a)m "commit message"
  - a 옵션의 경우, 신규 commit 대상 자원은 수행 불가
* git commit --amend
  - commit message 변경
  
#### git log
* git log
 - commit 목록 확인
* git log -p
  - commit 소스 코드 차이점 비교

#### git revert
* commit 취소하면서 새로운 버전 생성 (히스토리 남김)
* git revert [commit ID]

#### git revert
* commit 취소하면서 새로운 버전 생성 ( 히스토리 남김 )
* git revert [commit ID]

#### git reset
* commit 취소 (히스토리 남기지 않음)
* git reset [commit ID]
* git reset HEAD~2
  - 최근 2번째 commit 이력 되돌리기
* git reset --hard/soft/mixes HEAD
  - 최신 버전 get

#### git push
* git push origin [최종 repository에 반영할 branch 명]

### branch 관련 명령어
#### git branch
* git branch [생성할 branch명]
* git branch -v
  - branch 목록 확인
* git branch -d [제거할 branch명]

#### git checkout
* git checkout -(b) tmp
  - tmp branch 생성 후 해당 branch로 focus 변경
* git log --branches --decorate --graph --oneline
  - branch 반영 이력 한 눈에 보기
* git log master.tmp
  - master에는 없지만 tmp에는 존재하는 차이점 비교

#### git merge
* git merge tmp(master branch focus 상태에서 진행)
  - tmp 변경 사항을 master 로 merge
- merge 2가지 방식
1. Fast-forward : commit 생성 X (merge 할 branch HEAD 변경 없을 경우만 가능)
2. Merge made by the 'recursive' strategy : merge 작업 이후 새로운 commit 생성

#### git stash
* 감추다. 숨겨두다. 임시 저장소와 같은 역할
* git stash (save)
  - stash 백업
* git stash apply
  - stash 적용 
* git stash list
  - stash 목록 확인
* git stash drop
  - 최신 stash 제거
* git stash pop
  - apply + drop 동시 수행

#### 참고
1. local 작업 공간 : git working directory
2. git commit 대상 파일 : staging area, index
3. commit 저장 결과 : repository, cache


































