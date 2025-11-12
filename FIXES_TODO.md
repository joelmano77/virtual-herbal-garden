# Fixes TODO List

## Backend Fixes
- [x] Fix MongoDB URI in application.properties
- [x] Fix PlantService filter logic to support multiple filters
- [x] Add proper error handling in PlantController
- [ ] Add CORS configuration

## Frontend Fixes
- [x] Create centralized API service (api.js)
- [x] Fix VirtualTour.jsx - remove unused state and improve error handling
- [x] Improve PlantCard.jsx error handling
- [x] Create .env.example file

## Testing
- [ ] Test MongoDB connection (DNS issue with cluster)
- [ ] Test API endpoints (backend running but waiting for DB)
- [x] Test frontend-backend integration (frontend running on port 5174)
- [ ] Test filtering with multiple parameters (requires working DB)
