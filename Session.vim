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
badd +1 src/main/resources/data.sql
badd +62 ../../warships2/warships/src/main/java/com/lemonthe/java/AddCommand.java
badd +37 src/test/java/com/lemonthe/bookshelf/ApplicationTests.java
badd +24 ../../planes/planes/src/test/java/com/lemonthe/planes/PlanesApplicationTests.java
badd +1 ../../planes/planes/src/main/resources/application.properties
badd +1 src/main/resources/application.properties
badd +2 pom.xml
badd +39 ../../planes/planes/pom.xml
badd +18 src/main/resources/application.yml
badd +8 src/main/resources/templates/home.html
badd +1 src/main/java/com/lemonthe/bookshelf/web/HomeController.java
badd +67 ~/.config/nvim/init.vim
badd +84 src/main/resources/templates/books.html
badd +12 src/main/resources/templates/authors.html
badd +12 src/main/resources/templates/genres.html
badd +100 src/main/java/com/lemonthe/bookshelf/web/BookController.java
badd +40 src/main/java/com/lemonthe/bookshelf/web/BookService.java
badd +14 src/main/java/com/lemonthe/bookshelf/data/PathByStringConverter.java
badd +18 src/main/java/com/lemonthe/bookshelf/Photo.java
argglobal
%argdel
$argadd src/main/resources/schema.sql
tabnew +setlocal\ bufhidden=wipe
tabnew +setlocal\ bufhidden=wipe
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
let s:l = 27 - ((10 * winheight(0) + 5) / 11)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 27
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
let s:l = 63 - ((10 * winheight(0) + 5) / 11)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 63
normal! 04|
wincmd w
exe '1resize ' . ((&lines * 11 + 19) / 38)
exe '2resize ' . ((&lines * 11 + 19) / 38)
exe '3resize ' . ((&lines * 11 + 19) / 38)
tabnext
edit src/main/java/com/lemonthe/bookshelf/web/BookController.java
argglobal
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
let s:l = 100 - ((17 * winheight(0) + 17) / 35)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 100
normal! 057|
tabnext
edit src/main/resources/templates/books.html
argglobal
balt src/main/java/com/lemonthe/bookshelf/web/BookController.java
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
let s:l = 84 - ((18 * winheight(0) + 17) / 35)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 84
normal! 034|
tabnext
edit src/main/java/com/lemonthe/bookshelf/web/BookService.java
argglobal
balt src/main/java/com/lemonthe/bookshelf/web/BookController.java
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
let s:l = 32 - ((9 * winheight(0) + 17) / 35)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 32
normal! 0
tabnext
edit src/main/java/com/lemonthe/bookshelf/Photo.java
argglobal
balt src/main/java/com/lemonthe/bookshelf/data/PathByStringConverter.java
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
let s:l = 72 - ((23 * winheight(0) + 17) / 35)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 72
normal! 020|
tabnext
edit src/main/resources/schema.sql
let s:save_splitbelow = &splitbelow
let s:save_splitright = &splitright
set splitbelow splitright
wincmd _ | wincmd |
vsplit
1wincmd h
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
exe 'vert 1resize ' . ((&columns * 73 + 73) / 147)
exe 'vert 2resize ' . ((&columns * 73 + 73) / 147)
argglobal
balt src/main/resources/data.sql
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
let s:l = 26 - ((20 * winheight(0) + 17) / 35)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 26
normal! 021|
wincmd w
argglobal
if bufexists(fnamemodify("src/main/resources/data.sql", ":p")) | buffer src/main/resources/data.sql | else | edit src/main/resources/data.sql | endif
if &buftype ==# 'terminal'
  silent file src/main/resources/data.sql
endif
balt src/main/resources/schema.sql
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
let s:l = 1 - ((0 * winheight(0) + 17) / 35)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 1
normal! 0100|
wincmd w
exe 'vert 1resize ' . ((&columns * 73 + 73) / 147)
exe 'vert 2resize ' . ((&columns * 73 + 73) / 147)
tabnext 2
if exists('s:wipebuf') && len(win_findbuf(s:wipebuf)) == 0 && getbufvar(s:wipebuf, '&buftype') isnot# 'terminal'
  silent exe 'bwipe ' . s:wipebuf
endif
unlet! s:wipebuf
set winheight=1 winwidth=20
let &shortmess = s:shortmess_save
let &winminheight = s:save_winminheight
let &winminwidth = s:save_winminwidth
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
