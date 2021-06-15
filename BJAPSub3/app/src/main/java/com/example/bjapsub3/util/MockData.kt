package com.example.bjapsub3.util

import com.example.bjapsub3.data.source.local.entity.ContentEntity
import com.example.bjapsub3.data.source.remote.response.ContentResponse

object MockData {
    fun generateMockMovies(): List<ContentEntity> {

        val movies = ArrayList<ContentEntity>()

        movies.add(
            ContentEntity(
                "movies_1",
                "Bohemian Rhapsody",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "2018",
                "Music, Drama, History",
                "2h 15m",
                false,
                "R.drawable.poster_bohemian",
                "movies"
            )
        )
        movies.add(
            ContentEntity(
                "movies_2",
                "Parasite",
                "Parasite (Korean: 기생충; RR: Gisaengchung) is a 2019 South Korean black comedy thriller film directed by Bong Joon-ho, who co-wrote the screenplay with Han Jin-won. The film, starring Song Kang-ho, Lee Sun-kyun, Cho Yeo-jeong, Choi Woo-shik, Park So-dam, Jang Hye-jin, and Lee Jung-eun, follows a poor family who scheme to become employed by a wealthy family and infiltrate their household by posing as unrelated, highly qualified individuals.",
                "2019",
                "Comedy, Drama, Thriller",
                "2h 12m",
                false,
                "R.drawable.poster_parasite",
                "movies"
            )
        )
        movies.add(
            ContentEntity(
                "movies_3",
                "How to Train Your Dragon: \n The Hidden World",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "2019",
                "Animation, Family, Adventure",
                "1h 44m",
                false,
                "R.drawable.poster_how_to_train",
                "movies"
            )
        )
        movies.add(
            ContentEntity(
                "movies_4",
                "Joker",
                "In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime. This path brings him face-to-face with his alter-ego: the Joker.",
                "2019",
                "Crime, Drama, Thriller",
                "2h 2min",
                false,
                "R.drawable.poster_joker",
                "movies"
            )
        )
        movies.add(
            ContentEntity(
                "movies_5",
                "The Dark Night",
                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
                "2008",
                "Action, Crime, Drama",
                "2h 32min",
                false,
                "R.drawable.poster_the_dark_knight",
                "movies"
            )
        )
        movies.add(
            ContentEntity(
                "movies_6",
                "The Matrix",
                "When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.",
                "1999",
                "Action, Sci-Fi",
                "2h 16m",
                false,
                "R.drawable.poster_the_matrix",
                "movies"
            )
        )
        movies.add(
            ContentEntity(
                "movies_7",
                "Mortal Kombat",
                "MMA fighter Cole Young seeks out Earth's greatest champions in order to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "2021",
                "Action, Adventure, Fantasy",
                "1h 50m",
                false,
                "R.drawable.poster_mortal_kombat",
                "movies"
            )
        )
        movies.add(
            ContentEntity(
                "movies_8",
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "2018",
                "Action, Adventure, Fantasy",
                "2h 23m",
                false,
                "R.drawable.poster_aquaman",
                "movies"
            )
        )
        movies.add(
            ContentEntity(
                "movies_9",
                "Scoob!",
                "Scooby and the gang face their most challenging mystery ever: a plot to unleash the ghost dog Cerberus upon the world. As they race to stop this dogpocalypse, the gang discovers that Scooby has an epic destiny greater than anyone imagined.",
                "2020",
                "Animation, Adventure, Comedy",
                "1h 33m",
                false,
                "R.drawable.poster_scoob",
                "movies"
            )
        )
        movies.add(
            ContentEntity(
                "movies_10",
                "Godzilla vs Kong",
                "The epic next chapter in the cinematic Monsterverse pits two of the greatest icons in motion picture history against one another - the fearsome Godzilla and the mighty Kong - with humanity caught in the balance.",
                "2021",
                "Action, Sci-Fi, Thriller",
                "1h 53m",
                false,
                "R.drawable.poster_godzilla_kong",
                "movies"
            )
        )

        return movies
    }

    fun generateRemoteMockMovies(): List<ContentResponse> {

        val movies = ArrayList<ContentResponse>()

        movies.add(
            ContentResponse(
                "movies_1",
                "Bohemian Rhapsody",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "2018",
                "Music, Drama, History",
                "2h 15m",
                "R.drawable.poster_bohemian",
                "movies"
            )
        )
        movies.add(
            ContentResponse(
                "movies_2",
                "Parasite",
                "Parasite (Korean: 기생충; RR: Gisaengchung) is a 2019 South Korean black comedy thriller film directed by Bong Joon-ho, who co-wrote the screenplay with Han Jin-won. The film, starring Song Kang-ho, Lee Sun-kyun, Cho Yeo-jeong, Choi Woo-shik, Park So-dam, Jang Hye-jin, and Lee Jung-eun, follows a poor family who scheme to become employed by a wealthy family and infiltrate their household by posing as unrelated, highly qualified individuals.",
                "2019",
                "Comedy, Drama, Thriller",
                "2h 12m",
                "R.drawable.poster_parasite",
                "movies"
            )
        )
        movies.add(
            ContentResponse(
                "movies_3",
                "How to Train Your Dragon: \n The Hidden World",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "2019",
                "Animation, Family, Adventure",
                "1h 44m",
                "R.drawable.poster_how_to_train",
                "movies"
            )
        )
        movies.add(
            ContentResponse(
                "movies_4",
                "Joker",
                "In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime. This path brings him face-to-face with his alter-ego: the Joker.",
                "2019",
                "Crime, Drama, Thriller",
                "2h 2min",
                "R.drawable.poster_joker",
                "movies"
            )
        )
        movies.add(
            ContentResponse(
                "movies_5",
                "The Dark Night",
                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
                "2008",
                "Action, Crime, Drama",
                "2h 32min",
                "R.drawable.poster_the_dark_knight",
                "movies"
            )
        )
        movies.add(
            ContentResponse(
                "movies_6",
                "The Matrix",
                "When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.",
                "1999",
                "Action, Sci-Fi",
                "2h 16m",
                "R.drawable.poster_the_matrix",
                "movies"
            )
        )
        movies.add(
            ContentResponse(
                "movies_7",
                "Mortal Kombat",
                "MMA fighter Cole Young seeks out Earth's greatest champions in order to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "2021",
                "Action, Adventure, Fantasy",
                "1h 50m",
                "R.drawable.poster_mortal_kombat",
                "movies"
            )
        )
        movies.add(
            ContentResponse(
                "movies_8",
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "2018",
                "Action, Adventure, Fantasy",
                "2h 23m",
                "R.drawable.poster_aquaman",
                "movies"
            )
        )
        movies.add(
            ContentResponse(
                "movies_9",
                "Scoob!",
                "Scooby and the gang face their most challenging mystery ever: a plot to unleash the ghost dog Cerberus upon the world. As they race to stop this dogpocalypse, the gang discovers that Scooby has an epic destiny greater than anyone imagined.",
                "2020",
                "Animation, Adventure, Comedy",
                "1h 33m",
                "R.drawable.poster_scoob",
                "movies"
            )
        )
        movies.add(
            ContentResponse(
                "movies_10",
                "Godzilla vs Kong",
                "The epic next chapter in the cinematic Monsterverse pits two of the greatest icons in motion picture history against one another - the fearsome Godzilla and the mighty Kong - with humanity caught in the balance.",
                "2021",
                "Action, Sci-Fi, Thriller",
                "1h 53m",
                "R.drawable.poster_godzilla_kong",
                "movies"
            )
        )

        return movies
    }

    fun generateMockTvShow(): List<ContentEntity> {

        val tvShow = ArrayList<ContentEntity>()

        tvShow.add(
            ContentEntity(
                "tvShow_1",
                "Naruto Shippūden",
                "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                "2007",
                "Animation, Action & Adventure, Sci-Fi & Fantasy",
                "25m per episode",
                false,
                "R.drawable.poster_naruto_shipudden",
                "tvShow"
            )
        )
        tvShow.add(
            ContentEntity(
                "tvShow_2",
                "Chernobyl",
                "In April 1986, an explosion at the Chernobyl nuclear power plant in the Union of Soviet Socialist Republics becomes one of the world's worst man-made catastrophes.",
                "2019",
                "Drama, History, Thriller",
                "5 episode",
                false,
                "R.drawable.poster_chernobyl",
                "tvShow"
            )
        )
        tvShow.add(
            ContentEntity(
                "tvShow_3",
                "Breaking Bad",
                "A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine in order to secure his family's future.",
                "2008",
                "Crime, Drama, Thriller",
                "62 episode",
                false,
                "R.drawable.poster_breaking_bad",
                "tvShow"
            )
        )
        tvShow.add(
            ContentEntity(
                "tvShow_4",
                "Sherlock",
                "A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London.",
                "2010",
                "Crime, Drama, Mystery",
                "15 Episode",
                false,
                "R.drawable.poster_sherlock",
                "tvShow"
            )
        )
        tvShow.add(
            ContentEntity(
                "tvShow_5",
                "Death Note",
                "A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London.",
                "2006",
                "Animation, Crime, Drama",
                "12 Episode",
                false,
                "R.drawable.poster_death_note",
                "tvShow"
            )
        )
        tvShow.add(
            ContentEntity(
                "tvShow_6",
                "Hunter x Hunter",
                "Gon Freecss aspires to become a Hunter, an exceptional being capable of greatness. With his friends and his potential, he seeks for his father who left him when he was younger.",
                "2011",
                "Animation, Action, Adventure",
                "148 episode",
                false,
                "R.drawable.poster_hunter_x_hunter",
                "tvShow"
            )
        )
        tvShow.add(
            ContentEntity(
                "tvShow_7",
                "Designated Survivor",
                "A low-level Cabinet member becomes President of the United States after a catastrophic attack kills everyone above him in the line of succession.",
                "2016",
                "Action, Drama, Mystery",
                "53 episode",
                false,
                "R.drawable.poster_designated_survivor",
                "tvShow"
            )
        )
        tvShow.add(
            ContentEntity(
                "tvShow_8",
                "Friends",
                "Follows the personal and professional lives of six twenty to thirty-something-year-old friends living in Manhattan.",
                "1994",
                "Comedy, Romance",
                "235 episode",
                false,
                "R.drawable.poster_friends",
                "tvShow"
            )
        )
        tvShow.add(
            ContentEntity(
                "tvShow_9",
                "Narcos",
                "A chronicled look at the criminal exploits of Colombian drug lord Pablo Escobar, as well as the many other drug kingpins who plagued the country through the years.",
                "2015",
                "Biography, Crime, Drama",
                "30 episode",
                false,
                "R.drawable.poster_narcos",
                "tvShow"
            )
        )
        tvShow.add(
            ContentEntity(
                "tvShow_10",
                "One Piece",
                "Follows the adventures of Monkey D. Luffy and his pirate crew in order to find the greatest treasure ever left by the legendary Pirate, Gold Roger. The famous mystery treasure named \"One Piece\".",
                "1999",
                "Animation, Action, Adventure",
                "977 episode",
                false,
                "R.drawable.poster_one_piece",
                "tvShow"
            )
        )
        return tvShow
    }


    fun generateRemoteTvShow(): List<ContentResponse> {

        val tvShow = ArrayList<ContentResponse>()

        tvShow.add(
            ContentResponse(
                "tvShow_1",
                "Naruto Shippūden",
                "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                "2007",
                "Animation, Action & Adventure, Sci-Fi & Fantasy",
                "25m per episode",
                "R.drawable.poster_naruto_shipudden",
                "tvShow"
            )
        )
        tvShow.add(
            ContentResponse(
                "tvShow_2",
                "Chernobyl",
                "In April 1986, an explosion at the Chernobyl nuclear power plant in the Union of Soviet Socialist Republics becomes one of the world's worst man-made catastrophes.",
                "2019",
                "Drama, History, Thriller",
                "5 episode",
                "R.drawable.poster_chernobyl",
                "tvShow"
            )
        )
        tvShow.add(
            ContentResponse(
                "tvShow_3",
                "Breaking Bad",
                "A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine in order to secure his family's future.",
                "2008",
                "Crime, Drama, Thriller",
                "62 episode",
                "R.drawable.poster_breaking_bad",
                "tvShow"
            )
        )
        tvShow.add(
            ContentResponse(
                "tvShow_4",
                "Sherlock",
                "A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London.",
                "2010",
                "Crime, Drama, Mystery",
                "15 Episode",
                "R.drawable.poster_sherlock",
                "tvShow"
            )
        )
        tvShow.add(
            ContentResponse(
                "tvShow_5",
                "Death Note",
                "A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London.",
                "2006",
                "Animation, Crime, Drama",
                "12 Episode",
                "R.drawable.poster_death_note",
                "tvShow"
            )
        )
        tvShow.add(
            ContentResponse(
                "tvShow_6",
                "Hunter x Hunter",
                "Gon Freecss aspires to become a Hunter, an exceptional being capable of greatness. With his friends and his potential, he seeks for his father who left him when he was younger.",
                "2011",
                "Animation, Action, Adventure",
                "148 episode",
                "R.drawable.poster_hunter_x_hunter",
                "tvShow"
            )
        )
        tvShow.add(
            ContentResponse(
                "tvShow_7",
                "Designated Survivor",
                "A low-level Cabinet member becomes President of the United States after a catastrophic attack kills everyone above him in the line of succession.",
                "2016",
                "Action, Drama, Mystery",
                "53 episode",
                "R.drawable.poster_designated_survivor",
                "tvShow"
            )
        )
        tvShow.add(
            ContentResponse(
                "tvShow_8",
                "Friends",
                "Follows the personal and professional lives of six twenty to thirty-something-year-old friends living in Manhattan.",
                "1994",
                "Comedy, Romance",
                "235 episode",
                "R.drawable.poster_friends",
                "tvShow"
            )
        )
        tvShow.add(
            ContentResponse(
                "tvShow_9",
                "Narcos",
                "A chronicled look at the criminal exploits of Colombian drug lord Pablo Escobar, as well as the many other drug kingpins who plagued the country through the years.",
                "2015",
                "Biography, Crime, Drama",
                "30 episode",
                "R.drawable.poster_narcos",
                "tvShow"
            )
        )
        tvShow.add(
            ContentResponse(
                "tvShow_10",
                "One Piece",
                "Follows the adventures of Monkey D. Luffy and his pirate crew in order to find the greatest treasure ever left by the legendary Pirate, Gold Roger. The famous mystery treasure named \"One Piece\".",
                "1999",
                "Animation, Action, Adventure",
                "977 episode",
                "R.drawable.poster_one_piece",
                "tvShow"
            )
        )
        return tvShow
    }
}