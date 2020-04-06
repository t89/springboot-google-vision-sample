package com.example.bath

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(userRepository: UserRepository,
                            articleRepository: ArticleRepository) = ApplicationRunner {

        val samiUser = User("staaibi", "Samira", "Taaibi")
        val sami = userRepository.save(samiUser)
        articleRepository.save(Article(
                title = "Tolle Veggie-Wurst",
                headline = "Tolle Headline",
                content = "Toller Content",
                author = sami
        ))
        articleRepository.save(Article(
                title = "Bli",
                headline = "Bla",
                content = "Blubb",
                author = sami
        ))
    }
}