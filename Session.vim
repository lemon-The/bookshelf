let SessionLoad = 1
let s:so_save = &g:so | let s:siso_save = &g:siso | setg so=0 siso=0 | setl so=-1 siso=-1
let v:this_session=expand("<sfile>:p")
silent only
silent tabonly
cd ~/projects_java/bookshelf/bookshelf
if expand('%') == '' && !&modified && line('$') <= 1 && getline(1) == ''
  let s:wipebuf = bufnr('%')
endif
let s:shortmess_save = &shortmess
if &shortmess =~ 'A'
  set shortmess=aoOA
else
  set shortmess=aoO
endif
badd +89 src/main/resources/templates/books.html
badd +60 src/main/resources/templates/authors.html
badd +19 src/main/resources/templates/genres.html
badd +31 src/main/java/com/lemonthe/bookshelf/web/controllers/BookController.java
badd +15 src/main/java/com/lemonthe/bookshelf/data/BookRepository.java
badd +24 src/main/resources/schema.sql
badd +119 src/main/java/com/lemonthe/bookshelf/web/services/BookService.java
badd +54 src/test/java/com/lemonthe/bookshelf/ApplicationTests.java
badd +38 src/main/java/com/lemonthe/bookshelf/web/services/AuthorService.java
badd +28 src/main/java/com/lemonthe/bookshelf/web/services/GenreService.java
badd +105 src/main/java/com/lemonthe/bookshelf/web/controllers/GenreController.java
badd +75 src/main/java/com/lemonthe/bookshelf/web/controllers/AuthorController.java
badd +14 src/main/resources/data.sql
argglobal
%argdel
edit src/main/java/com/lemonthe/bookshelf/web/controllers/GenreController.java
argglobal
balt src/main/resources/templates/genres.html
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let &fdl = &fdl
let s:l = 35 - ((6 * winheight(0) + 17) / 35)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 35
normal! 05|
tabnext 1
if exists('s:wipebuf') && len(win_findbuf(s:wipebuf)) == 0 && getbufvar(s:wipebuf, '&buftype') isnot# 'terminal'
  silent exe 'bwipe ' . s:wipebuf
endif
unlet! s:wipebuf
set winheight=1 winwidth=20
let &shortmess = s:shortmess_save
let s:sx = expand("<sfile>:p:r")."x.vim"
if filereadable(s:sx)
  exe "source " . fnameescape(s:sx)
endif
let &g:so = s:so_save | let &g:siso = s:siso_save
set hlsearch
nohlsearch
doautoall SessionLoadPost
unlet SessionLoad
" vim: set ft=vim :
