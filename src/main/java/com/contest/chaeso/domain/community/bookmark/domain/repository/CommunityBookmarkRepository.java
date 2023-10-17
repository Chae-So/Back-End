package com.contest.chaeso.domain.community.bookmark.domain.repository;

import com.contest.chaeso.domain.community.bookmark.domain.CommunityBookmark;
import com.contest.chaeso.domain.community.community.domain.Community;
import com.contest.chaeso.domain.users.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityBookmarkRepository extends JpaRepository<CommunityBookmark, Long>, CommunityBookmarkQueryRepository {

    Optional<CommunityBookmark> findByCommunityAndUsers(Community community, Users users);
}
