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
badd +1 src/main/resources/schema.sql
badd +9 ../../planes/planes/src/main/resources/schema.sql
badd +38 src/main/java/com/lemonthe/bookshelf/Book.java
badd +30 src/main/java/com/lemonthe/bookshelf/Genre.java
badd +1 src/main/java/com/lemonthe/bookshelf/Author.java
badd +9 src/main/resources/data.sql
badd +62 ../../warships2/warships/src/main/java/com/lemonthe/java/AddCommand.java
badd +37 src/test/java/com/lemonthe/bookshelf/ApplicationTests.java
badd +24 ../../planes/planes/src/test/java/com/lemonthe/planes/PlanesApplicationTests.java
badd +1 ../../planes/planes/src/main/resources/application.properties
badd +1 src/main/resources/application.properties
badd +2 pom.xml
badd +39 ../../planes/planes/pom.xml
badd +18 src/main/resources/application.yml
badd +8 src/main/resources/templates/home.html
badd +89 src/main/java/com/lemonthe/bookshelf/web/HomeController.java
badd +67 ~/.config/nvim/init.vim
badd +42 src/main/resources/templates/books.html
badd +42 src/main/resources/templates/authors.html
badd +49 src/main/resources/templates/genres.html
argglobal
%argdel
$argadd src/main/resources/schema.sql
tabnew +setlocal\ bufhidden=wipe
tabnew +setlocal\ bufhidden=wipe
tabnew +setlocal\ bufhidden=wipe
tabrewind
edit src/main/java/com/lemonthe/bookshelf/Author.java
let s:save_splitbelow = &splitbelow
let s:save_splitright = &splitright
set splitbelow splitright
wincmd _ | wincmd |
split
wincmd _ | wincmd |
split
2wincmd k
wincmd w
wincmd w
let &splitbelow = s:save_splitbelow
let &splitright = s:save_splitright
wincmd t
let s:save_winminheight = &winminheight
let s:save_winminwidth = &winminwidth
set winminheight=0
set winheight=1
set winminwidth=0
set winwidth=1
exe '1resize ' . ((&lines * 11 + 19) / 38)
exe '2resize ' . ((&lines * 11 + 19) / 38)
exe '3resize ' . ((&lines * 11 + 19) / 38)
argglobal
balt src/main/java/com/lemonthe/bookshelf/Genre.java
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
let s:l = 26 - ((9 * winheight(0) + 5) / 11)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 26
normal! 013|
wincmd w
argglobal
if bufexists(fnamemodify("src/main/java/com/lemonthe/bookshelf/Genre.java", ":p")) | buffer src/main/java/com/lemonthe/bookshelf/Genre.java | else | edit src/main/java/com/lemonthe/bookshelf/Genre.java | endif
if &buftype ==# 'terminal'
  silent file src/main/java/com/lemonthe/bookshelf/Genre.java
endif
balt src/main/java/com/lemonthe/bookshelf/Author.java
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
let s:l = 71 - ((0 * winheight(0) + 5) / 11)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 71
normal! 028|
wincmd w
argglobal
if bufexists(fnamemodify("src/main/java/com/lemonthe/bookshelf/Book.java", ":p")) | buffer src/main/java/com/lemonthe/bookshelf/Book.java | else | edit src/main/java/com/lemonthe/bookshelf/Book.java | endif
if &buftype ==# 'terminal'
  silent file src/main/java/com/lemonthe/bookshelf/Book.java
endif
balt src/main/java/com/lemonthe/bookshelf/Genre.java
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
let s:l = 1 - ((0 * winheight(0) + 5) / 11)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 1
normal! 04|
wincmd w
exe '1resize ' . ((&lines * 11 + 19) / 38)
exe '2resize ' . ((&lines * 11 + 19) / 38)
exe '3resize ' . ((&lines * 11 + 19) / 38)
tabnext
edit src/main/java/com/lemonthe/bookshelf/web/HomeController.java
argglobal
balt src/main/resources/templates/home.html
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
let s:l = 152 - ((34 * winheight(0) + 17) / 35)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 152
normal! 026|
tabnext
edit src/main/resources/templates/books.html
argglobal
balt src/main/java/com/lemonthe/bookshelf/web/HomeController.java
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
let s:l = 42 - ((16 * winheight(0) + 17) / 35)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 42
normal! 043|
tabnext
edit src/main/resources/templates/genres.html
argglobal
balt src/main/resources/templates/books.html
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
let s:l = 49 - ((12 * winheight(0) + 17) / 35)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 49
normal! 038|
tabnext 2
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
doautoall SessionLoadPost
unlet SessionLoad
" vim: set ft=vim :
