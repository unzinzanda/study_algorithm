SELECT ITEM_ID, ITEM_NAME
FROM ITEM_INFO NATURAL JOIN ITEM_TREE
WHERE PARENT_ITEM_ID IS NULL
ORDER BY ITEM_ID